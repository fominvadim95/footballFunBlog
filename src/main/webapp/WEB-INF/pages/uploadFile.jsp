<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Upload file</title>
    <style>
        body {
            background-color: #E0EEE0;
        }
        #name, #file {
            width: 280px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Enter folder name and choose the file</h3>
    <form method="POST" action="/funBlog/uploadFile" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Folder name:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Enter folder name"/>
        </div>

        <div class="form-group">
            <label for="file">File:</label>
            <input type="file" class="form-control" name="file" id="file"/>
        </div>

        <button type="submit" class="btn btn-primary" id="restore">Upload</button>
    </form>
</div>
</body>
</html>