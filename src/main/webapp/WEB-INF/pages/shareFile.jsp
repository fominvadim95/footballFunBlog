<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Share file</title>
    <style>
        body {
            background-color: #E0EEE0;
        }

        #name, #username {
            width: 250px;
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
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users to sharing <span
                    class="caret"></span></a>
                <ul class="dropdown-menu" id="users">

                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav">
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Files to sharing <span
                    class="caret"></span></a>
                <ul class="dropdown-menu" id="files">

                </ul>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <h3>Enter file name</h3>
    <form method="POST" action="/funBlog/shareFile">
        <div class="form-group">
            <label for="username">User name:</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Enter user name"/>
        </div>
        <div class="form-group">
            <label for="file">File name:</label>
            <input type="text" class="form-control" name="name" id="file" placeholder="Enter file name"/>
        </div>

        <button type="submit" class="btn btn-primary" id="restore">Share</button>
    </form>
</div>
<script src="/resources/js/users.js">
</script>
<script src="/resources/js/files.js">
</script>
</body>
</html>
