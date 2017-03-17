<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Main</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #E0EEE0;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/funBlog">Football Fun Blog</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/funBlog/createFolder">Create Folder</a></li>
            <li><a href="/funBlog/removeFile">Remove folder or file</a></li>
            <li><a href="/funBlog/readFolder">Read Folder</a></li>
            <li><a href="/funBlog/uploadFile">Upload file</a></li>
            <li><a href="/funBlog/downloadFile">Download file</a></li>
            <li><a href="/funBlog/shareFile">Share file</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h3>All options</h3>
    <p>Use this page for files operation performing</p>
</div>

</body>
</html>