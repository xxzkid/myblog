define(function(require, exports, module) {
	var Clipboard = require('Clipboard');
	
	var setData = function(txt) {
		var btnDom = $('<button style="display:none;" id="j-cilpboard">copy</button>');
		$('body').append(btnDom);
		var cb = new Clipboard('#j-cilpboard', {
			text : function() {
				return txt;
			}
		});
		cb.on('success', function(e) {
		    console.log('Action:', e.action);
		    console.log('Text:', e.text);
		    console.log('Trigger:', e.trigger);
		    e.clearSelection();
		});
		cb.on('error', function(e) {
		    console.error('Action:', e.action);
		    console.error('Trigger:', e.trigger);
		});
		btnDom.click().remove();
		cb.destroy();
	};
	
	exports.setData = setData;
});