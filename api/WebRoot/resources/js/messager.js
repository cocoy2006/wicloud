function messager(message, style, delay) {
	$('.messager').remove();
	genrate(message, style);
	if(delay > 0) {
		window.setTimeout(function() {
			$('.messager').remove();
		}, delay);
	}
}

function genrate(message, style) {
	var div = 
		'<div class="messager alert alert-' + style + ' alert-dismissible" role="alert" ' +
				'style="position: fixed; text-align: center; top: 0; width: 100%; z-index: 1999;">' + 
  			'<button type="button" class="close" data-dismiss="alert">' +
  				'<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
  			'</button>' +
  			message +
		'</div>';
	$("body").prepend(div);
}

function success() {
	messager(' 操作成功,2秒后自动刷新. ', 'success', 0);
	setTimeout(function() {
		window.location.reload();
	}, 1600);
}

function danger() {
	messager(' 系统异常，请稍后重试. ', 'danger', 2000);
}