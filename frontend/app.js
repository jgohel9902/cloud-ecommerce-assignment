// === API BASE URLS (use your Render endpoints) ===
const PRODUCT_API_BASE = "https://productservice-3mzc.onrender.com";
const CART_API_BASE = "https://cartservice-zcpd.onrender.com";

// Utility: format price
function formatPrice(value) {
  return `$${value.toFixed(2)}`;
}

// =============================
// PRODUCTS PAGE LOGIC (index.html)
// =============================
async function loadProducts() {
  const grid = document.getElementById("productsGrid");
  if (!grid) return; // we are on cart.html, ignore

  try {
    const res = await fetch(`${PRODUCT_API_BASE}/products`);
    if (!res.ok) {
      throw new Error("Failed to load products");
    }
    const products = await res.json();

    // Render cards
    grid.innerHTML = "";
    products.forEach((p) => {
      const card = document.createElement("article");
      card.className = "card";

      const imageUrl = p.image || "https://via.placeholder.com/300x200?text=Shoe";

      card.innerHTML = `
        <img src="${imageUrl}" alt="${p.name}">
        <div class="card-title">${p.name}</div>
        <div class="card-sku">SKU: ${p.sku}</div>
        <p class="card-description">${p.description || ""}</p>
        <div class="card-footer">
          <span class="price">${formatPrice(p.price)}</span>
          <button class="primary-btn">Add to Cart</button>
        </div>
      `;

      // Add click handler for Add to Cart
      const btn = card.querySelector("button");
      btn.addEventListener("click", () => addToCart(p));

      grid.appendChild(card);
    });

    // Update cart count bubble
    await refreshCartCount();
  } catch (err) {
    console.error(err);
    grid.innerHTML = `<p style="color:red;">Failed to load products. Check console / backend.</p>`;
  }
}

// Add product to cart (calls POST /cart)
async function addToCart(product) {
  try {
    const payload = {
      productId: product.id,
      name: product.name,
      price: product.price,
      sku: product.sku,
      image: product.image,
      quantity: 1
    };

    const res = await fetch(`${CART_API_BASE}/cart`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    if (!res.ok) {
      throw new Error("Failed to add to cart");
    }

    alert(`Added "${product.name}" to cart.`);
    await refreshCartCount();
  } catch (err) {
    console.error(err);
    alert("Could not add item to cart. Check console / backend.");
  }
}

// Get cart count (just length of GET /cart)
async function refreshCartCount() {
  const badge = document.getElementById("cart-count");
  if (!badge) return;

  try {
    const res = await fetch(`${CART_API_BASE}/cart`);
    if (!res.ok) return;
    const items = await res.json();
    badge.textContent = items.length;
  } catch {
    // ignore silently on count
  }
}

// =============================
// CART PAGE LOGIC (cart.html)
// =============================
async function loadCart() {
  const container = document.getElementById("cart-items");
  const totalSpan = document.getElementById("cart-total");
  const emptyBtn = document.getElementById("empty-cart-btn");

  if (!container || !totalSpan || !emptyBtn) return; // we are on index.html

  emptyBtn.addEventListener("click", async () => {
    const confirmClear = confirm("Are you sure you want to empty the cart?");
    if (!confirmClear) return;

    try {
      const res = await fetch(`${CART_API_BASE}/emptycart`, {
        method: "DELETE",
      });
      if (!res.ok) throw new Error("Failed to empty cart");
      await renderCartItems();
    } catch (err) {
      console.error(err);
      alert("Failed to empty cart");
    }
  });

  await renderCartItems();
}

async function renderCartItems() {
  const container = document.getElementById("cart-items");
  const totalSpan = document.getElementById("cart-total");
  if (!container || !totalSpan) return;

  try {
    const res = await fetch(`${CART_API_BASE}/cart`);
    if (!res.ok) throw new Error("Failed to load cart");
    const items = await res.json();

    container.innerHTML = "";

    if (items.length === 0) {
      container.innerHTML = `<p class="empty-state">Your cart is empty. Go back and add some shoes!</p>`;
      totalSpan.textContent = "$0.00";
      return;
    }

    let total = 0;
    items.forEach((item) => {
      const quantity = item.quantity || 1;
      const lineTotal = item.price * quantity;
      total += lineTotal;

      const imageUrl = item.image || "https://via.placeholder.com/80x80?text=Shoe";

      const row = document.createElement("article");
      row.className = "cart-item";

      row.innerHTML = `
        <img src="${imageUrl}" alt="${item.name}">
        <div class="cart-item-details">
          <div class="cart-item-title">${item.name}</div>
          <div class="cart-item-meta">SKU: ${item.sku || ""} • Qty: ${quantity}</div>
          <div class="cart-item-price">${formatPrice(lineTotal)}</div>
        </div>
        <div class="cart-item-actions">
          <button>Remove</button>
        </div>
      `;

      const removeBtn = row.querySelector("button");
      removeBtn.addEventListener("click", () => removeCartItem(item.id));

      container.appendChild(row);
    });

    totalSpan.textContent = formatPrice(total);
  } catch (err) {
    console.error(err);
    container.innerHTML = `<p style="color:red;">Failed to load cart.</p>`;
    totalSpan.textContent = "$0.00";
  }
}

async function removeCartItem(id) {
  try {
    const res = await fetch(`${CART_API_BASE}/cart/${id}`, {
      method: "DELETE",
    });
    if (!res.ok) throw new Error("Failed to delete cart item");
    await renderCartItems();
  } catch (err) {
    console.error(err);
    alert("Could not remove item.");
  }
}

// =============================
// INIT (runs on each page)
// =============================
document.addEventListener("DOMContentLoaded", () => {
  loadProducts();
  loadCart();
});
