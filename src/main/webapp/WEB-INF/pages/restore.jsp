<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Restore</title>
    <style>
        body {
            background-color: #E0EEE0;
        }
        #name {
            width: 250px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Enter your login for password restoring</h3>
    <form method="POST" action="/funBlog/restore">
        <div class="form-group">
            <label for="name">Login:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Enter login"/>
        </div>

        <button type="submit" class="btn btn-warning" id="restore">Restore</button>
    </form>
</div>
</body>
</html>
