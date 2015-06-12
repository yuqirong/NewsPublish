function list(idstr) {
	var name1 = "subtree" + idstr;
	var name2 = "img" + idstr;
	var objectobj = document.all(name1);
	var imgobj = document.all(name2);

	// alert(imgobj);

	if (objectobj.style.display == "none") {
		for (i = 1; i < 10; i++) {
			var name3 = "img" + i;
			var name = "subtree" + i;
			var o = document.all(name);
			if (o != undefined) {
				o.style.display = "none";
				var image = document.all(name3);
				// alert(image);
				image.src = "/NewsPublish/images/ico04.gif";
			}
		}
		objectobj.style.display = "";
		imgobj.src = "/NewsPublish/images/ico03.gif";
	} else {
		objectobj.style.display = "none";
		imgobj.src = "/NewsPublish/images/ico04.gif";
	}
}