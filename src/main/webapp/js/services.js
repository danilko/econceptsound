//
// document ready
// Initialize functions once document loading is complete
//
$(document).ready(
		function() {
			// Auto refresh list if sign in for every 30 seconds
			var intVar = setInterval(function() {
				getTracks()
			}, 300000);
			
			getTracks();
}); // $(document).ready(function(){})


// getTracks
// Retrieve list of tracks and display them
// Input: None
// OUtput: None
//
function getTracks() 
{
	// Perform AJAX Call
	$
			.ajax({
				url : 'rest/track?trackNum=10&startIndex=0',
				type : "GET",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(pData, pTextStatus, pRequest) {

					$("#services").html('');
					// Get the file list length
					var lTrackListLength = pData.length;

					
					// Prepare table to display file list
					var lTrackHTMLString = '';

					// Loop through the file list and concate file into
					// table row
					for ( var lTrackIndex = 0; lTrackIndex < lTrackListLength; lTrackIndex++) 
					{
						
						
						lTrackHTMLString = lTrackHTMLString + '<div class="row">';
						lTrackHTMLString = lTrackHTMLString + '<div class="col-sm-6 col-md-4">';
						// Thumbnail
						lTrackHTMLString = lTrackHTMLString + '<div class="thumbnail"><img src="' + pData[lTrackIndex].artwork_url + '" onError="imgError(this);"></div>';
						
						lTrackHTMLString = lTrackHTMLString + '</div>';
						
						lTrackHTMLString = lTrackHTMLString + '<div class="col-sm-8"><div class="caption">';
						lTrackHTMLString = lTrackHTMLString + '<h3><a href="' + pData[lTrackIndex].permalink_url + '">' + pData[lTrackIndex].title + '</a></h3>';
						lTrackHTMLString = lTrackHTMLString + '<p>By <a href="' + pData[lTrackIndex].user.permalink_url + '">' + pData[lTrackIndex].user.username + '</a></p>';
						lTrackHTMLString = lTrackHTMLString + '<p>' + pData[lTrackIndex].genre + '</p>';
						lTrackHTMLString = lTrackHTMLString + '<p><audio controls  preload="none"><source src="' + pData[lTrackIndex].stream_url + '" type="audio/ogg" /><em>Sorry, your browser does not support HTML5 audio.</em></audio></p>';
						lTrackHTMLString = lTrackHTMLString + '</div></div>';
						
						lTrackHTMLString = lTrackHTMLString + '</div>';
					}  // for
					
					// Add the table into the file list div
					$("#services").append(lTrackHTMLString);
				}, // success: function(pData)
				error : function(xhr, status, thrown) 
				{
					// Print out status and message
					$("#divStatus")
							.html(
							'<div class="alert alert-danger" ><a class="close" data-dismiss="alert" href="#">x</a><h4 class="alert-heading">Server Error</h4> Server is under maintence, please come back later</div>');
				} // error : function (xhr, status, thrown)
			}); // $.ajax
} // function confirmUserCredentials

function showTrackModal()
{
	$('#divTrackModal').show();
}  // function showTrackModal

function imgError(pImage)
{
	pImage.onerror="";
	pImage.src="img/no_image.jpg";
	return true;
}  // function imgError