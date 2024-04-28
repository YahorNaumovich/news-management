<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title></title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .content {
      text-align: center;
    }

    .container {
      width: 300px;
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      text-align: center;
      margin: 0 auto;
    }

    h1 {
      color: #333;
      text-align: center;
    }

    .links {
      margin-top: 20px;
    }

    .links a {
      display: inline-block;
      color: #007bff;
      text-decoration: none;
      margin: 0 10px;
      padding: 8px 16px;
      border: 2px solid #007bff;
      border-radius: 4px;
      transition: background-color 0.3s ease;
    }

    .links a:hover {
      background-color: #007bff;
      color: #fff;
    }

    .anonymous {
      margin-top: 20px;
    }

    .anonymous a {
      display: block;
      color: #007bff;
      text-decoration: none;
    }

    .anonymous a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>

  <div class="content">
    <h1>Welcome to our News Portal</h1>
    <div class="container">
      <div class="links">
        <a href="Controller?command=GO_TO_LOGIN_PAGE">Sign In</a>
        <span>or</span>
        <a href="Controller?command=GO_TO_REGISTRATION_PAGE">Sign Up</a>
      </div>
      <div class="anonymous">
        <p><a href="Controller?command=GO_TO_MAIN_PAGE">Sign in as Anonymous</a></p>
      </div>
    </div>
  </div>
</body>
</html>