function initItemArray(url) {
	var itemArray = new Array();
	$.ajax({
		url: url,
		dataType: "json",
		async: false,
		success: function(data) {
			$.each(data, function(index, item) {
				itemArray.push(item);
			});
		}
	});
	return itemArray;
}

function initSubItemMap(url) {
	var subItemMap = new Map();
	$.ajax({
		url: url,
		dataType: "json",
		async: false,
		success: function(data) {
			$.each(data, function(index, subItem) {
				var subItemArray = null;
				if(subItemMap.containsKey(subItem.itemId)) {
					subItemArray = subItemMap.get(subItem.itemId);
				} else {
					subItemArray = new Array();
				}
				subItemArray.push(subItem);
				subItemMap.put(subItem.itemId, subItemArray);
			});
		}
	});
	return subItemMap;
}