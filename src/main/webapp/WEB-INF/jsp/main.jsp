<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>News Portal</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f8f9fa; /* Very light gray */
    }
    header {
      background-color: #007bff; /* Blue */
      color: #fff;
      padding: 10px 20px;
      position: sticky;
      top: 0;
      z-index: 1000;
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Add shadow */
    }
    header h1 {
      margin: 0;
    }
    nav a {
      color: #fff;
      text-decoration: none;
      margin-right: 20px;
    }
    .auth-links {
      margin-left: auto;
    }
    .auth-links a {
      color: #f8f9fa;
      text-decoration: none;
      margin-left: 20px;
    }
    .auth-links a:hover {
      text-decoration: underline; /* Underline on hover */
    }
    main {
      padding: 20px;
      display: grid;
      grid-template-columns:1fr 1fr 1fr 1fr;
    }
.news-item {
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin: 10px;
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease; /* Add transition effect */
}

.news-item:hover {
  transform: translateY(-5px) scale(1.05); /* Move the item up by 5px and scale it up by 5% on hover */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Add a larger shadow on hover */
}
    .news-title {
      font-size: 24px;
      margin: 0;
      color: #007bff; /* Blue */
    }
    .news-description {
      color: #555;
    }
  </style>
</head>
<body>

<header>
  <h1>News Portal</h1>
</header>

<main>
  <div class="news-item">
    <h2 class="news-title">Breaking News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla quam velit, vulputate eu pharetra nec, mattis ac neque. Morbi quis placerat massa. Sed et leo at mauris mollis scelerisque.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consecem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
    <p class="news-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
  </div>

  <!-- Repeat the same structure for other news items -->

</main>

</body>
</html>
