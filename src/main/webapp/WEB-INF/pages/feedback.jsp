<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Feedback</title>
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
        </div>
    </nav>
    <form id="contactForm" action="/funBlog/feedback" method="POST">
        <div class="row">
                <div class="form-group">
                    <label for="subject" class="control-label">Enter the subject</label>
                    <select id="subject" name="subject" class="form-control">
                        <option value="error">Error</option>
                        <option value="recommendation">Recommendation</option>
                    </select>
                    <span class="glyphicon form-control-feedback"></span>
                </div>

        <div class="form-group">
            <label for="message" class="control-label">Enter the message</label>
            <textarea id="message" name="message" class="form-control" rows="10" placeholder="Message"  maxlength="500" required="required" class="form-control"></textarea>
        </div>
        </div>

        <hr>
        <button type="submit" class="btn btn-primary pull-right">Send</button>
    </form>
</div>
</body>
</html>
