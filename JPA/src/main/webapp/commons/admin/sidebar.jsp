<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style> 
.sidebar { min-height: 100vh; } 
.sidebar .nav-link.active { background-color: #0d6efd; color: #fff; }
</style>
<nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link text-white" href="/admin/dashboard">
                    <i class="bi bi-speedometer2"></i> Dashboard
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/JPA/admin/products">
                    <i class="bi bi-box-seam"></i> Sản phẩm
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/JPA/admin/list-category">
                    <i class="bi bi-table"></i> Danh mục
                </a>
            </li>
        </ul>
    </div>
</nav>
<script>
    // Lấy URL hiện tại (pathname thôi, bỏ domain)
    const currentPath = window.location.pathname;

    // Lặp qua tất cả nav-link
    document.querySelectorAll('.sidebar .nav-link').forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active'); // Thêm class active
        }
    });
</script>

