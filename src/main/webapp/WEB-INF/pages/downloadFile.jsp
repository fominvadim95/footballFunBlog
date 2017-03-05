<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Download file</title>
</head>
<body>
<div class="container">
    <h3>Enter file name</h3>
    <form method="POST" action="/funBlog/downloadFile">
        <div class="form-group">
            <label for="name">File name:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Enter file name"/>
        </div>

        <button type="submit" class="btn btn-default" id="restore">Download</button>
    </form>
</div>
</body>
</html>