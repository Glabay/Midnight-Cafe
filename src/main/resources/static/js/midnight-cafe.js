/**
 * Midnight Cafe - Client Script
 */
document.addEventListener('DOMContentLoaded', () => {
    // Password toggle functionality
    const toggleButtons = document.querySelectorAll('.toggle-password');
    toggleButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const input = btn.parentElement.querySelector('input');
            if (input) {
                const isPassword = input.type === 'password';
                input.type = isPassword ? 'text' : 'password';
                btn.setAttribute('aria-label', isPassword ? 'Hide password' : 'Show password');
            }
        });
    });

    // Menu category filter
    const filterBtns = document.querySelectorAll('.menu-filter-btn');
    const menuCards = document.querySelectorAll('.menu-card');

    if (filterBtns.length > 0 && menuCards.length > 0) {
        filterBtns.forEach(btn => {
            btn.addEventListener('click', () => {
                filterBtns.forEach(b => b.classList.remove('active'));
                btn.classList.add('active');

                const filter = btn.getAttribute('data-filter');
                menuCards.forEach(card => {
                    const category = card.getAttribute('data-category');
                    if (filter === 'all' || category === filter) {
                        card.style.display = 'flex';
                    } else {
                        card.style.display = 'none';
                    }
                });
            });
        });
    }
});
