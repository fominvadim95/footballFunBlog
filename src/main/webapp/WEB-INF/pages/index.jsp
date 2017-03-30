<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Main</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://api-maps.yandex.ru/2.1/?lang=en_US&load=Geolink"
            type="text/javascript"></script>
    <script src="https://api-maps.yandex.ru/2.1/?lang=en_US" type="text/javascript"></script>
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

    <div id="map" style="width: 1200px; height: 400px"></div>
    <div class="row">
        <div class="col-md-4">
        <br/>
            <form method="POST" action="/funBlog/createPlace">
                <div class="form-group">
                    <input type="text" class="form-control" name="categoryName" id="categoryName"
                           placeholder="Category name"/>
                    <input type="text" class="form-control" name="placeName" id="placeName" placeholder="Place name"/>
                    <input type="text" class="form-control" name="city" id="city" placeholder="City"/>
                    <input type="number" class="form-control" name="x" id="x" placeholder="X cord" step="any"/>
                    <input type="number" class="form-control" name="y" id="y" placeholder="Y cord" step="any"/>
                    <textarea class="form-control" rows="10" cols="45" name="description" id="description"
                              placeholder="Description"></textarea>
                </div>
                <button type="submit" class="btn btn-primary" id="save">Save</button>
            </form>
        </div>
        <div class="col-md-4">
            <br/>
            <form method="POST" action="/funBlog/findPlace">
                <div class="form-group">
                    <input type="text" class="form-control" name="category" id="category" placeholder="Category name"/>
                </div>
                <button type="submit" class="btn btn-primary" id="find">Find</button>
            </form>
        </div>


        <div class="col-md-4" id="places">
            <h2>Places</h2>
            <c:forEach var="place" items="${places}" >
                <div id="${place.city}" class="place">
                    <p class="text-muted">Name: ${place.name}</p>
                    <p class="text-muted">City: ${place.city}</p>
                    <p class="text-muted">Description: ${place.description}</p>
                    <a href="#" id="delete">Delete</a>
                    <span class="ymaps-geolink">${place.city}</span>
                </div>
                <br/>
            </c:forEach>
        </div>
        <div id="cords">
            <c:forEach var="point" items="${points}">
                <p class="x">${point.latitude}</p>
                <p classs="y">${point.longitude}</p>
            </c:forEach>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    ymaps.ready(function () {
        var myMap = new ymaps.Map("map", {
            center: [55.76, 37.64],
            zoom: 2
        });

        var places = $(".places").children(".place");
        for(var i=0; i<places.length;i++){
            var x = places[i].children(".xCord");
            var y = places[i].children(".yCord");
            console.log("x :"+x);
            var myPlacemark = new ymaps.Placemark([x, y]);
            myMap.geoObjects.add(myPlacemark);
        }

        myMap.events.add('click', function (e) {
            var coords = e.get('coords');
            console.log(coords[0]);
            $("#x").val(coords[0]);
            $("#y").val(coords[1]);
        });

        $("#delete").click(function (e) {
            e.preventDefault();
            var city = $(this).parent().attr("id");
            console.log(city);
            $.ajax({
                url: "/funBlog/deletePlace", data: JSON.stringify(city),
                contentType: "application/json", type: "POST", success: function () {
                    $("#" + city).hide();
                }
            });

        });
    });
</script>
</body>
</html>