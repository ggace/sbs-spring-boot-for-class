$('select[data-value]').each(function(index, el) {
  const $el = $(el);

  const defaultValue = $el.attr('data-value').trim();

  if ( defaultValue.length > 0 ) {
	$el.val(defaultValue);	
  }
}); 

function getParam(sname) {

    var params = location.search.substr(location.search.indexOf("?") + 1);

    var sval = "";

    params = params.split("&");

    for (var i = 0; i < params.length; i++) {

        temp = params[i].split("=");

        if ([temp[0]] == sname) { sval = temp[1]; }

    }

    return sval;

}

function increaseHitCount() {
	$.get("/usr/article/increaseHitCount",{
	      id: getParam("id"),
	    ajaxMode: "Y"
	}, function(data){
	    $(".article-detail__hit-count").empty().html(`조회수 ${data.data}`);
	}, 'json');
}


	
setTimeout(()=> increaseHitCount(), 0)