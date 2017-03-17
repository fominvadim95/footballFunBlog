<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Remove file</title>
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
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/funBlog">Football Fun Blog</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Folders to removing <span
                        class="caret"></span></a>
                    <ul class="dropdown-menu" id="folders">

                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Files to removing<span
                        class="caret"></span></a>
                    <ul class="dropdown-menu" id="files">

                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <h3>Enter file/folder name</h3>
    <form method="POST" action="/funBlog/removeFile">
        <div class="form-group">
            <label for="name">File/folder name:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Enter file name"/>
        </div>

        <button type="submit" class="btn btn-danger" id="restore">Remove</button>
    </form>
</div>
<script>

    $.ajax({
        type: 'GET',
        url: '/funBlog/folders',
        dataType: 'json',
        async: true,
        success: function (folders) {
            var foldersNode = document.getElementById("folders");
            for (var i = 0; i < folders.length; i++) {
                var li = document.createElement("li");
                var ref = document.createElement("a");
                ref.setAttribute("href", "#");
                ref.appendChild(document.createTextNode(folders[i]))
                li.appendChild(ref);
                foldersNode.appendChild(li);
            }
        }
    });

    $.ajax({
        type: 'GET',
        url: '/funBlog/files',
        dataType: 'json',
        async: true,
        success: function (files) {
            var filesNode = document.getElementById("files");
            for (var i = 0; i < files.length; i++) {
                var li = document.createElement("li");
                var ref = document.createElement("a");
                ref.setAttribute("href", "#");
                ref.appendChild(document.createTextNode(files[i]))
                li.appendChild(ref);
                filesNode.appendChild(li);
            }
        }
    });
</script>
</body>
</html>