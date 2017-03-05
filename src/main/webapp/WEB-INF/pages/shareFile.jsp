<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Share file</title>
</head>
<body>
<div class="container">
    <h3>Enter file name</h3>
    <form method="POST" action="/funBlog/shareFile">
        <div class="form-group">
            <label for="username">User name:</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Enter user name"/>
        </div>
        <div class="form-group">
            <label for="name">File name:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Enter file name"/>
        </div>

        <button type="submit" class="btn btn-default" id="restore">Share</button>
    </form>
</div>
</body>
</html>
