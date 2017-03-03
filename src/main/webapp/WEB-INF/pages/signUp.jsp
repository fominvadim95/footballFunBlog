<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Sign Up</title>
</head>
<body>
<div class="container">
    <h3>Welcome, Enter The Personal Details</h3>
    <form:form method="POST" action="/funBlog/signUp" modelAttribute="user">
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input type="text" class="form-control" path="name" id="name" placeholder="Enter name"/>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <form:password class="form-control" path="password" id="pwd" placeholder="Enter password"/>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <form:input type="email" class="form-control" path="email" id="email" placeholder="Enter email"/>
        </div>
        <div class="form-group">
            <label for="age">Age:</label>
            <form:input type="number" class="form-control" path="age" id="age" placeholder="Enter age"/>
        </div>
        <div class="form-group">Sex
            <div class="radio">
                <label><form:radiobutton path="sex" value="Male"/>Male</label>
            </div>
            <div class="radio">
                <label><form:radiobutton path="sex" value="Female"/>Female</label>
            </div>
        </div>
        <div class="form-group">
            <label for="country">Country:</label>
            <form:input type="text" class="form-control" path="country" id="country" placeholder="Enter country"/>
        </div>
        <button type="submit" class="btn btn-default" id="register">Sign up</button>
    </form:form>
</div>
</body>
</html>
