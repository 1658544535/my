tt.text = tt.bh.ext({
	h:function()
	{
		if (this.needHandle()) {
			this.render(tt.Conf.errCls, this.v.getI18(this.f.label), "talentClose");
			tt.addCls(this.e, tt.Conf.errInputCls);
		}
	}
});