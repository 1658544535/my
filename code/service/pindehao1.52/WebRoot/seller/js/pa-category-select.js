var categoryJson = {};

categoryJson["0"] = ["玩具文体", "美妆个护", "居家百货"];
categoryJson["0-0"] = ["响铃/布书/爬行", "早教/智能玩具", "木制/拆装/悠悠球", "彩泥/仿真", "遥控/惯性/发条", "机器人/模型", "其他玩具文体", "电子词典/电纸书", "文化用品", "书籍/报纸/杂志", "专业乐器"];
categoryJson["0-0-0"] = ["布书/书偶", "爬行垫/游戏毯", "学步/健身架", "叠叠乐/不倒翁"];
categoryJson["0-0-1"] = ["学习早教玩具", "其他早教玩具", "音乐类玩具", "涂画类玩具"];
categoryJson["0-0-2"] = ["其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-3"] = ["3其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-4"] = ["4其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-5"] = ["5其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-6"] = ["6其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-7"] = ["7其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-8"] = ["8其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-9"] = ["9其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-0-10"] = ["10其他", "游泳池/戏水玩具", "游乐设备/大型设施", "运动配件"];
categoryJson["0-1"] = ["响铃/布书/爬行", "早教/智能玩具", "木制/拆装/悠悠球", "彩泥/仿真", "遥控/惯性/发条", "机器人/模型", "其他玩具文体"];
categoryJson["0-1-0"] = ["响铃/布书/爬行", "响铃/布书/爬行", "响铃/布书/爬行", "响铃/布书/爬行", "响铃/布书/爬行"];
categoryJson["0-1-1"] = ["早教/智能玩具", "早教/智能玩具", "早教/智能玩具", "早教/智能玩具", "早教/智能玩具"];
categoryJson["0-1-2"] = ["木制/拆装/悠悠球", "木制/拆装/悠悠球", "木制/拆装/悠悠球", "木制/拆装/悠悠球", "木制/拆装/悠悠球"];
categoryJson["0-1-3"] = ["彩泥/仿真", "彩泥/仿真", "彩泥/仿真", "彩泥/仿真", "彩泥/仿真"];
categoryJson["0-1-4"] = ["遥控/惯性/发条", "遥控/惯性/发条", "遥控/惯性/发条", "遥控/惯性/发条", "遥控/惯性/发条"];
categoryJson["0-1-5"] = ["机器人/模型", "机器人/模型", "机器人/模型", "机器人/模型", "机器人/模型"];
categoryJson["0-1-6"] = ["其他玩具文体", "其他玩具文体", "其他玩具文体", "其他玩具文体", "其他玩具文体"];
categoryJson["0-2"] = ["2响铃/布书/爬行", "2早教/智能玩具", "2木制/拆装/悠悠球", "2彩泥/仿真", "2遥控/惯性/发条", "2机器人/模型", "2其他玩具文体"];
categoryJson["0-2-0"] = ["2响铃/布书/爬行", "2响铃/布书/爬行", "2响铃/布书/爬行", "2响铃/布书/爬行", "2响铃/布书/爬行"];
categoryJson["0-2-1"] = ["2早教/智能玩具", "早教/智能玩具", "早教/智能玩具", "早教/智能玩具", "早教/智能玩具"];
categoryJson["0-2-2"] = ["2木制/拆装/悠悠球", "木制/拆装/悠悠球", "木制/拆装/悠悠球", "木制/拆装/悠悠球", "木制/拆装/悠悠球"];
categoryJson["0-2-3"] = ["2彩泥/仿真", "彩泥/仿真", "彩泥/仿真", "彩泥/仿真", "彩泥/仿真"];
categoryJson["0-2-4"] = ["2遥控/惯性/发条", "遥控/惯性/发条", "遥控/惯性/发条", "遥控/惯性/发条", "遥控/惯性/发条"];
categoryJson["0-2-5"] = ["2机器人/模型", "机器人/模型", "机器人/模型", "机器人/模型", "机器人/模型"];
categoryJson["0-2-6"] = ["2其他玩具文体", "其他玩具文体", "其他玩具文体", "其他玩具文体", "其他玩具文体"];

$(function(){
	var category = '';
	
	for(fatheritem in categoryJson[0]){
		$(".pa-category-list").eq(0).find("ul#fatheritem").append('<li class="view-CategoryListItem" data-num="'+ fatheritem +'"><span op-value="name">' + categoryJson[0][fatheritem] + '</span><span op-value="nextIcon"><i class="iconfont">&#xf016d;</i></span></li>');
	}
	
	$(document).delegate("li.view-CategoryListItem","click",function(){
		var _this = $(this),
			CategoryList_num = $("div.view-CategoryList").index($(this).parents("div.view-CategoryList")),
			CategoryLis_data = $(this).attr("data-num");
		category='';
		switch (CategoryList_num) {
			case 0 : 
				$("li.view-CategoryListItem").removeClass("active");
				_this.addClass("active");
				var CategoryListItem_num = "0-" + CategoryLis_data;
				$("div.view-CategoryList").eq(1).find("ul").html("");
				$("div.view-CategoryList").eq(2).find("ul").html("");
				for(CategoryListItem in categoryJson[CategoryListItem_num]){
					$(".pa-category-list").eq(1).find("ul").append('<li class="view-CategoryListItem" data-num="'+ CategoryListItem +'"><span op-value="name">' + categoryJson[CategoryListItem_num][CategoryListItem] + '</span><span op-value="nextIcon"><i class="iconfont">&#xf016d;</i></span></li>');
				}
				$(".product-add-bigbtn").addClass("ui-button-ldisable");
				break;
				
			case 1 : 
				$("div.view-CategoryList:eq(2) li.view-CategoryListItem").removeClass("active");
				_this.addClass("active").siblings().removeClass("active");
				var fatheritem_data = $("div.view-CategoryList:eq(0) li.view-CategoryListItem.active").attr("data-num");
				var CategoryListItem_num = "0-" + fatheritem_data + "-" + CategoryLis_data;
				$("div.view-CategoryList").eq(2).find("ul").html("");
				for(CategoryListItem in categoryJson[CategoryListItem_num]){
					$(".pa-category-list").eq(2).find("ul").append('<li class="view-CategoryListItem" data-num="'+ CategoryListItem +'"><span op-value="name">' + categoryJson[CategoryListItem_num][CategoryListItem] + '</span><span op-value="nextIcon"><i class="iconfont">&#xf016d;</i></span></li>');
				}
				$(".product-add-bigbtn").addClass("ui-button-ldisable");
				break;
				
			case 2 :
				_this.addClass("active").siblings().removeClass("active");
				for(var i=0;i<3;i++){
					if(i==2){
						category += $("li.view-CategoryListItem.active").eq(i).find("span:eq(0)").html()
					}else{
						category += $("li.view-CategoryListItem.active").eq(i).find("span:eq(0)").html() + " - ";
					}
				}
				$(".product-add-bigbtn").removeClass("ui-button-ldisable")
					.prop("href","add_v2.html");
				break;
		}
		$(".product-add-note .view-CurrentSelection").html(category);
				
	});
	
})







































