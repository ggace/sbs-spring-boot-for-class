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

function getHitCount(){
	$.get("/usr/article/getHitCount",{
	      id: getParam("id"),
	    ajaxMode: "Y"
	}, function(data){
	    $(".article-detail__hit-count").empty().html(`조회수 ${data.data1}`);
	}, 'json');
}

function increaseHitCount() {
	
	const localStorageKey = `article__${getParam("id")}__viewDone`;
	
	
	
	if(localStorage.getItem(localStorageKey)){
		getHitCount();
		return;
	}
	
	localStorage.setItem(localStorageKey, true);
	
	$.get("/usr/article/doIncreaseHitCount",{
	      id: getParam("id"),
	    ajaxMode: "Y"
	}, function(data){
	    $(".article-detail__hit-count").empty().html(`조회수 ${data.data1}`);
	}, 'json');
	
	
}

increaseHitCount()