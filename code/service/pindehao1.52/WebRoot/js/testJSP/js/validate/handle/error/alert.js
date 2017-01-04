tt.alert = tt.bh.ext({
	h:function()
	{
		if (this.needHandle()){
			tt.addCls(this.e, tt.Conf.errInputCls);
	        alert(this.v.getI18(this.f.label));
		}
	}
});