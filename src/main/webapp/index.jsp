<html>


<head>



<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
	type="text/javascript"></script>

<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>

<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css"
	rel="stylesheet">
<link href="https://cdn.quilljs.com/1.3.6/quill.bubble.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">





</head>
<body>
	<h2>Hello World!</h2>




<div id="editordiv">
		<button class="backbtn" onclick=" showstorieslistdiv()">
			<i class="fa fa-arrow-left"></i>
		</button>

		<div id="toolbar"></div>

		<div id="fullContainer">

			<input type="text" id="inserttitle" name="inserttitle"
				placeholder="Title"> <br /> <br />
			<div id="editor"></div>
			<br /> <br />
			<button id="upload" onclick="uploadclick(1)" value="true">Publish
				As Story</button>
			<button id="draft" onclick="draftclick(2)" value="true">Save
				As Draft</button>

		</div>
	</div>





	<script> 



	var toolbarOptions = [['bold', 'italic', 'underline', 'strike'],
		['blockquote', 'code-block'],
		[{ 'header': [1, 2, 3, 4, 5, 6, false] }],
		[{ 'list': 'ordered' }, { 'list': 'bullet' }],
		[{ 'script': 'sub' }, { 'script': 'super' }],
		[{ 'indent': '-1' }, { 'indent': '+1' }],
		[{ 'direction': 'rtl' }],
		[{ 'size': ['small', false, 'large', 'huge'] }],
		['image', 'video', 'formula'],
		[{ 'color': [] }, { 'background': [] }],
		[{ 'font': [] }],
		[{ 'align': [] }]
		];

		var quill = new Quill('#editor', {
			modules: {
				toolbar: toolbarOptions
			},
			theme: 'snow'
		});

		var content = "";
		var html = "";
		var base64str = "";
		var contentsrcpath = "";

		function uploadclick(type) {


			content = quill.container.innerHTML;
			html = new DOMParser().parseFromString(content, "text/html");
			result = [...html.images].map(e => e.src);
			base64str = result[0];
			
			$.ajax({
				url: 'imageservice',
				type: 'POST',
				data: {
					encodedbase64: base64str,
				},
				cache: false,
				success: function(data) {
					console.log(data)
				},
				error: function(){
					}
			});
		}

	</script>

</body>
</html>
