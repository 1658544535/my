/**
 * baseHandler
 */
tt.bh = tt.C.ext({
	setV:function(v)
	{
		this.v = v;
		return this;
	},
	setE:function(e)
	{
		this.e = e;
		return this;
	},
	setF:function(f)
	{
		this.f = f;
		return this;
	},
	setVal:function(val)
	{
		this.val = val;
		return this;
	},
	setIndex:function(index)
	{
		this.index = index;
		return this;
	},
	needHandle:function()
	{
		if (this.e.style.display == 'none' || this.e.disabled)//对于不可见的元素,不处理
        {
            return false;
        }
        return true;
	},
	render:function(cls, msg, closeCls) {
			var types = tt.inputType(this.e);
			var div = document.createElement("div");
			
			var msgId = this.f.getMsgId(this.e);
			if (msgId) {
				tt.getById(msgId).appendChild(div);
			} else {
				if (types.isCheckbox || types.isRadio) {
					tt.moveToPos(div, this.f.es[this.f.es.length - 1]);
				} else {
					tt.moveToPos(div, this.e);
				}
				this.e.parentNode.insertBefore(div, this.e);
				tt.vf.msgs.push({"msg":div,"ele":this.e});
			}
			
			div.id = this.e[tt.Conf.proNameOfMsgId];
			div.className = cls;
			div.innerHTML = msg;
			
			var close = document.createElement("div");
			div.appendChild(close);
			
			close.className = closeCls;
			close.innerHTML = "X";
			close.title = tt.close;
			
			tt.Util.addEventHandler(close, "click", new tt.closeHandler(div, close).h);
	}
});