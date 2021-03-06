function TopUpAuto(){
	
}
(function(A)
{
		A("body").ready(function()
				{
				Menu.displayButtons("#menu_topup");
				Menu.enableSettings(A("#_uid").val());
				TopUpAuto.enableTopUpPagination();
				TopUpAuto.enableTopUpInvoice();
				A("#topup_form").validate(
				{
							errorElement:"p",
							wrapper:"span",
							errorPlacement:function(B,C)
							{
								B.addClass("error");
								C.after(B);
							},
							rules:{amount:{required:true,number:true}},
							submitHandler:function(B)
							{
								A("#topup_submit_container").hide();
								A("#topup_wait_container").show();
								A("#topup_form").submit();
							}
				});
				A("#topup_disable_reload_link").click(function()
				{
					admin.disableAutoReload(function(B)
					{
							A("#welcome_message").hide();
							window.location.reload();
					});
				});
				A("#top_search_link").click(function()
				{
					TopUpAuto.loadPurchases(1);
					return false;
				});
			});
		TopUpAuto.loadPurchases=function(B)
		{
			A("#topup_search_wait_container").show();
			view.getView("/widget/search/purchase?p="+B,function(C)
			{
				A("#topup_search_container").html(C);
				A("#topup_search_wait_container").hide();
				TopUpAuto.enablePurchasePagination();
			});
		};
		TopUpAuto.enablePurchasePagination=function()
		{
			A(".topup_purchase_link").each(function(B,C)
			{
				A(this).click(function()
				{
					TopUpAuto.loadPurchases(A(this).attr("rel"));
					return false;
				});
			});
		};
		TopUpAuto.reloadTopUp=function(B)
		{
			view.getView("/widget/search/topup?p="+B,function(C)
			{
				A("#topup_result_container").html(C);
				TopUpAuto.enableTopUpPagination();
				TopUpAuto.enableTopUpInvoice();
			});
		};
		TopUpAuto.enableTopUpPagination=function()
		{
			A(".topup_pagination_link").each(function(B,C)
				{
					A(this).click(function()
					{
						TopUpAuto.reloadTopUp(A(this).attr("rel"));
						return false;
					});
				});
		};
		TopUpAuto.enableTopUpInvoice=function()
		{
			A(".get_invoice_link").each(function(B,C)
			{
				A(this).click(function()
				{
					admin.generateTopUpInvoice(A(this).attr("rel"),function(D)
					{
						if(D==0)
						{
							alert("The invoice has been sent via email.");
						}else
						{
							if(D==2)
							{
								alert("You need to fill out your company details in the profile section.");
							}else
							{
								if(D==3)
								{
									alert("The invoice has been sent already, if you want a copy contact our help desk.");
								}else
								{
									alert("We are not able to generate this invoice, please contact our help desk.");
								}
							}
						}
					});
					return false;
				});
			});
		};
})(jQuery);