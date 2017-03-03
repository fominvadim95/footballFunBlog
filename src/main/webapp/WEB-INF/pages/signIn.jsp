<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Sign In</title>
</head>
<body>
<div class="container">
    <h3>Welcome, Enter The Name And Password</h3>
    <form:form method="POST" action="/funBlog/signIn" modelAttribute="user">
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input type="text" class="form-control" path="name" id="name" placeholder="Enter name"/>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <form:password class="form-control" path="password" id="pwd" placeholder="Enter password"/>
        </div>
        <button type="submit" class="btn btn-default" id="authorize">Sign in</button>
        <button type="button" class="btn btn-default" id="register"
                onclick="window.location='/funBlog/signUp'">Sign up
        </button>
        <button type="button" class="btn btn-default" id="restore"
                onclick="window.location='/funBlog/restore'">Restore password
        </button>
    </form:form>
</div>
</body>
</html>
