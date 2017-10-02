DragNDrop = {
		setPDFTextContent : function(result){
			/*var item_container = '<div class="block clearfix">'+ 
			'<div class="block-actions pull-right"><div class="remove modifier remove-block">'+
			'<i class="fa fa-times"></i></div><div class="action modifier copy-block">'+
			'<i class="fa fa-repeat"></i></div><div class="edit modifier edit-block">'+
			'<i class="fa fa-pencil"></i></div></div></div>';*/
	
		var item_container = '';
		var results = result['result'];
		results.forEach(function(element){
			if(element){
				item_container += '<div class="block clearfix"> <div class="block-actions pull-right">'+
				' <div class="edit modifier edit-block"><i class="fa fa-pencil"></i></div></div><span>'+element['body'] + '</span></div>';
				console.log(1);
			}
		});
		$('#textItems .item-container').html(item_container);
		$('#textItems .item-container .block .edit-block').on('click', function(event){
			var textVal = $(event.target).closest('.block.clearfix').find('span').html();
			console.log(textVal);
			$('#disclaimerModal .modal-body > textarea ').val(textVal);
			$('#disclaimerModal .modal-body > textarea ').html(textVal);
			$('#disclaimerModal').modal();
			$("#disclaimerModal").on('hidden.bs.modal', function () {
				console.log('closing modal');
				var modifiedVal = $('#disclaimerModal .modal-body > textarea ').val();
				console.log("modified val " + modifiedVal);
				$('#disclaimerModal .modal-body > textarea ').html("");
				$('#disclaimerModal .modal-body > textarea ').val("");
				$(event.target).closest('.block.clearfix').find('span').html(modifiedVal);
				$(this).unbind('hidden.bs.modal');
			    $(this).data('bs.modal', null);
			});
		});
		},
		setPDFImageContent: function(result){
			var item_container = '';
			var results = result['images'];
			results.forEach(function(element){
				if(element){
					item_container += '<div class="block clearfix" style="background-repeat:no-repeat;background-size:cover;background-image: url('+element['url']+
					');"></div>';
					console.log(1);
				}
			});
			$('#imageItems .item-container').html(item_container);
		},
		initializeDragNDrop : function(){
				$(".row").sortable({
					axis : "x",
					items : ".column"
				});
				$(".container")
						.sortable(
								{
									axis : "y",
									items : ".row",
									placeholder : 'block-placeholder',
									revert : 150,
									start : function(e, ui) {

										placeholderHeight = ui.item.outerHeight();
										ui.placeholder.height(placeholderHeight + 15);
										$(
												'<div class="block-placeholder-animator" data-height="' + placeholderHeight + '"></div>')
												.insertAfter(ui.placeholder);

									},
									change : function(event, ui) {

										ui.placeholder.stop().height(0).animate({
											height : ui.item.outerHeight() + 15
										}, 300);

										placeholderAnimatorHeight = parseInt($(
												".block-placeholder-animator").attr(
												"data-height"));

										$(".block-placeholder-animator")
												.stop()
												.height(placeholderAnimatorHeight + 15)
												.animate(
														{
															height : 0
														},
														300,
														function() {
															$(this).remove();
															placeholderHeight = ui.item
																	.outerHeight();
															$(
																	'<div class="block-placeholder-animator" data-height="' + placeholderHeight + '"></div>')
																	.insertAfter(
																			ui.placeholder);
														});

									},
									stop : function(e, ui) {

										$(".block-placeholder-animator").remove();

									},
								});

				// Block Controls
				$(".blocks")
						.sortable(
								{
									connectWith : '.blocks',
									placeholder : 'block-placeholder',
									revert : 150,
									start : function(e, ui) {

										placeholderHeight = ui.item.outerHeight();
										ui.placeholder.height(placeholderHeight + 15);
										$(
												'<div class="block-placeholder-animator" data-height="' + placeholderHeight + '"></div>')
												.insertAfter(ui.placeholder);

									},
									change : function(event, ui) {

										ui.placeholder.stop().height(0).animate({
											height : ui.item.outerHeight() + 15
										}, 300);

										placeholderAnimatorHeight = parseInt($(
												".block-placeholder-animator").attr(
												"data-height"));

										$(".block-placeholder-animator")
												.stop()
												.height(placeholderAnimatorHeight + 15)
												.animate(
														{
															height : 0
														},
														300,
														function() {
															$(this).remove();
															placeholderHeight = ui.item
																	.outerHeight();
															$(
																	'<div class="block-placeholder-animator" data-height="' + placeholderHeight + '"></div>')
																	.insertAfter(
																			ui.placeholder);
														});

									},
									stop : function(e, ui) {

										$(".block-placeholder-animator").remove();

									},
								});
				$('.block-add')
						.click(
								function() {
									$(this)
											.closest('.column')
											.find('.blocks')
											.append(
													'<div class="block clearfix col-xs-3 h100"><div class="block-actions pull-right"><div class="remove modifier remove-block"><i class="fa fa-times"></i></div><div class="action modifier copy-block"><i class="fa fa-repeat"></i></div><div class="edit modifier edit-block"><i class="fa fa-pencil"></i></div></div></div>');
								});

				// Rows
				$('.row-add')
						.click(
								function() {
									$('.builder-body')
											.append(
													'<div class="row well sortable"><div class="col-xs-6 column well sortable"></div><div class="col-xs-6 column well sortable"></div></div>');
								});
				$.fn.extend({
					removeclasser : function(re) {
						return this.each(function() {
							var c = this.classList
							for (var i = c.length - 1; i >= 0; i--) {
								var classe = "" + c[i]
								if (classe.match(re))
									c.remove(classe)
							}
						})
						return re;
					},
					translatecolumn : function(columns) {
						var grid = [];
						var items = columns.split(',');
						for (i = 0; i < items.length; ++i) {
							if (items[i] == '1') {
								grid.push(12);
							}
							if (items[i] == '2') {
								grid.push(6);
							}
							if (items[i] == '3') {
								grid.push(4);
							}
						}
						return grid;
					}
				});

				// Column Controls
				$(".row-toolbar").disableSelection();

				$('.column-option')
						.click(
								function() {
									var grid = $.fn.translatecolumn($(this).data('split')
											.toString());
									var columns = $(this).closest('.row').find('.column');
									for (i = 0; i < grid.length; ++i) {
										if (columns[i]) {
											$(columns[i]).removeclasser('col-');
											$(columns[i]).addClass('col-xs-' + grid[i]);
										} else {
											// Create column with class
											$(columns[i])
													.append(
															'<div class="col-xs-6 column well sortable"><div class="blocks">');
										}
										// If less columns than existing then merge
									}
								});
			
		},
		initializeGridStack : function(){
			var options = {
			        cell_height: 50,
			        vertical_margin: 10
			    };
			    $('.grid-stack').gridstack(options);
		}
}

$(document).ready(function(){
	
	    
	    $('.overlay').show();
	    $.ajax({
	          // Uncomment the following to send cross-domain cookies:
	          //xhrFields: {withCredentials: true},
	          url: "rest/pdf/text/",
	          dataType: 'json',
	      }).always(function () {
	    	  console.log("always");
	      }).done(function (result) {
	          console.log(result);
	          DragNDrop.setPDFTextContent(result);
	          $.ajax({
		          // Uncomment the following to send cross-domain cookies:
		          //xhrFields: {withCredentials: true},
		          url: "rest/pdf/images/",
		          dataType: 'json',
		      }).always(function () {
		    	  console.log("always images");
		      }).done(function (result) {
		    	  
		    	  DragNDrop.setPDFImageContent(result);
		          DragNDrop.initializeDragNDrop();
		          DragNDrop.initializeGridStack();
		          $('.overlay').hide();
		      });
	          
	      });
});