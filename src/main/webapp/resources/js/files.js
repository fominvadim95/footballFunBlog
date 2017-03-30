$.ajax({
    type: 'GET',
    url: '/funBlog/files',
    dataType: 'json',
    async: true,
    success: function (files) {
        var filesNode = document.getElementById("files");
        for (var i = 0; i < files.length; i++) {
            var li = document.createElement("li");
            li.setAttribute("class", files[i]);
            var ref = document.createElement("a");
            ref.appendChild(document.createTextNode(files[i]))
            ref.setAttribute("href", "?file=" + files[i]);
            li.appendChild(ref);
            filesNode.appendChild(li);
        }
    }
});
