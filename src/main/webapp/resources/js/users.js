$.ajax({
    type: 'GET',
    url: '/funBlog/users',
    dataType: 'json',
    async: true,
    success: function (users) {
        var usersNode = document.getElementById("users");
        for (var i = 0; i < users.length; i++) {
            console.log(users[i]);
            var li = document.createElement("li");
            var ref = document.createElement("a");
            ref.setAttribute("href","#");
            ref.appendChild(document.createTextNode(users[i]))
            li.appendChild(ref);
            usersNode.appendChild(li);
        }
    }
});


