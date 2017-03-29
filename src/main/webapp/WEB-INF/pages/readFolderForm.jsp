<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #E0EEE0;
        }

        #name {
            width: 250px;
        }
    </style>
    <title>Read folder</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/funBlog">Football Fun Blog</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Folder to search <span
                        class="caret"></span></a>
                    <ul class="dropdown-menu" id="folders">

                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <h3>Enter folder name</h3>
    <form method="POST" action="/funBlog/readFolder">
        <div class="form-group">
            <label for="folder">Folder name:</label>
            <input type="text" class="form-control" name="name" id="folder" placeholder="Enter folder name"/>
        </div>

        <button type="submit" class="btn btn-primary" id="restore">Read</button>
    </form>
</div>
<script src="/resources/js/folders.js"></script>
</body>
</html>
