<!DOCTYPE html>
<html>
<head>
    <title>Checkout Result</title>
</head>
<body>
    
    <h1>Checkout Result</h1>
    <p>${checkoutMessage}</p>
</body>
</html>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    h1 {
        text-align: center;
        color: #333;
    }
    form {
        background: #fff;
        padding: 20px;
        max-width: 500px;
        margin: 40px auto;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }
    input[type="text"],
    button[type="submit"] {
        width: 100%;
        padding: 8px;
        margin: 5px 0 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    button[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        cursor: pointer;
    }
    button[type="submit"]:hover {
        background-color: #45a049;
    }
    .success-message {
        background-color: #4CAF50; /* Updated color to green */
        color: white;
        padding: 10px;
        margin-bottom: 10px;
        border-radius: 4px;
    }
    .nav-links li {
        float: left;
    }
    .nav-links li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }
    .nav-links li a:hover {
        background-color: #ddd;
        color: black;
    }
</style>