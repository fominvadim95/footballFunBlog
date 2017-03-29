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
