'use strict';

var _typeof = typeof Symbol === "function"
		&& typeof Symbol.iterator === "symbol" ? function(obj) {
	return typeof obj;
}
		: function(obj) {
			return obj && typeof Symbol === "function"
					&& obj.constructor === Symbol ? "symbol" : typeof obj;
		};

var _createClass = function() {
	function defineProperties(target, props) {
		for (var i = 0; i < props.length; i++) {
			var descriptor = props[i];
			descriptor.enumerable = descriptor.enumerable || false;
			descriptor.configurable = true;
			if ("value" in descriptor)
				descriptor.writable = true;
			Object.defineProperty(target, descriptor.key, descriptor);
		}
	}
	return function(Constructor, protoProps, staticProps) {
		if (protoProps)
			defineProperties(Constructor.prototype, protoProps);
		if (staticProps)
			defineProperties(Constructor, staticProps);
		return Constructor;
	};
}();

function _classCallCheck(instance, Constructor) {
	if (!(instance instanceof Constructor)) {
		throw new TypeError("Cannot call a class as a function");
	}
}

var LunarHelp = function() {
	function LunarHelp(year, month, day) {
		_classCallCheck(this, LunarHelp);

		this.lunarInfo = new Array(0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260,
				0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6,
				0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0,
				0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54,
				0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50,
				0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
				0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0,
				0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0,
				0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6,
				0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950,
				0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4,
				0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0, 0x049b0,
				0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60,
				0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58,
				0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0,
				0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
				0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0,
				0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52,
				0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, 0x05aa0,
				0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250,
				0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577,
				0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0);

		this.nStr1 = new Array('日', '一', '二', '三', '四', '五', '六', '七', '八',
				'九', '十');
		this.nStr2 = new Array('初', '十', '廿', '三');

		var date = new Date(parseInt(year), parseInt(month) - 1, parseInt(day));

		var i, leap = 0, temp = 0; // 天数
		var baseDate = new Date(1900, 0, 31);
		var offset = (date - baseDate) / 86400000;

		// 计算年数
		for (i = 1900; i < 2050 && offset - this.lYearDays(i) > 0; i++) {
			offset -= this.lYearDays(i);
		}

		this.year = i;
		leap = this.leapMonth(i); // 闰哪个月
		this.isLeap = false;

		// 计算月数
		for (i = 1; i < 13 && offset > 0; i++) {
			// 闰月
			if (leap > 0 && i == leap + 1 && this.isLeap == false) {
				--i;
				temp = this.leapDays(this.year);
			} else {
				temp = this.monthDays(this.year, i);
			}

			// 解除闰月
			if (this.isLeap == true && i == leap + 1)
				this.isLeap = false;

			offset -= temp;
		}

		// 如果恰好减完了，不是闰月的话月数减1
		if (offset == 0 && leap > 0 && i == leap + 1)
			if (this.isLeap) {
				this.isLeap = false;
			} else {
				this.isLeap = true;
				--i;
			}

		if (offset < 0) {
			offset += temp;
			--i;
		}

		this.month = i;
		// 最后剩余的就是日期
		this.day = offset + 1;
	}

	// 获取y年的总天数

	_createClass(LunarHelp, [ {
		key : 'lYearDays',
		value : function lYearDays(year) {
			var i, sum = 0;
			for (i = 0x8000; i > 0x8; i >>= 1) {
				sum += this.lunarInfo[year - 1900] & i ? 30 : 29;
			}
			return sum + this.leapDays(year); // 最后在加上可能有的闰年的闰月
		}

	// 获取闰年闰月的天数 闰大月还是小月

	}, {
		key : 'leapDays',
		value: function leapDays(year) {
		    if (this.leapMonth(year)) return this.lunarInfo[year - 1900] & 0x10000 ? 30 : 29;else return 0;
		}

	// 获取闰年闰哪个月1-12 ,没闰传回 0

	}, {
		key : 'leapMonth',
		value : function leapMonth(year) {
			return this.lunarInfo[year - 1900] & 0xf;
		}

	// 获取y年m月的总天数 正常月

	}, {
		key : 'monthDays',
		value : function monthDays(year, month) {
			return this.lunarInfo[year - 1900] & 0x10000 >> month ? 30 : 29;
		}

	// 中文日期

	}, {
		key : 'cDay',
		value : function cDay(d) {
			var s;

			switch (d) {
			case 10:
				s = '初十';
				break;
			case 20:
				s = '二十';
				break;
			break;
		case 30:
			s = '三十';
			break;
		break;
	default:
		s = this.nStr2[Math.floor(d / 10)];
		s += this.nStr1[d % 10];
	}
	return s;
}
	// 中文月份

	}, {
		key : 'cMonth',
		value : function cMonth(m) {
			var s;

			switch (m) {
			case 1:
				s = '正月';
				break;
			case 2:
				s = '二月';
				break;
			case 3:
				s = '三月';
				break;
			case 4:
				s = '四月';
				break;
			case 5:
				s = '五月';
				break;
			case 6:
				s = '六月';
				break;
			case 7:
				s = '七月';
				break;
			case 8:
				s = '八月';
				break;
			case 9:
				s = '九月';
				break;
			case 10:
				s = '十月';
				break;
			case 11:
				s = '十一月';
				break;
			case 12:
				s = '十二月';
				break;
			default:
				break;
			}
			return s;
		}

	// 获得阴历日期 字符串

	}, {
		key : 'getLunarDay',
		value : function getLunarDay() {
			return cMonth(this.month) + cDay(this.day);
		}
	// 获得阴历日期某一天的中文

	}, {
		key : 'getLunarDayName',
		value : function getLunarDayName() {

			if (this.day == 1)
				return this.cMonth(this.month);
			return this.cDay(this.day);
		}
	// 获取阴历日期的数字

	}, {
		key : 'getLunarDayNum',
		value : function getLunarDayNum() {
			return {
				day : this.day,
				month : this.month
			};
		}
	} ]);

	return LunarHelp;
}();

// 定义全局的展示参数
var allShowInputArray = [];

var SimpleCalendar = function() {
	// 构造函数

	function SimpleCalendar(query, options) {
		_classCallCheck(this, SimpleCalendar);

		// 在日历中显示的文本对象集合
		this.showInputArr = [];

		// 当前日历控件本次选中时间缓存
		// 只保存日期时间
		// 每次日历控件被保存到日期集合后，本集合清空
		this.dateCheckList = new ArrayList();

		// 当前日历控件本次未选中时间缓存
        // 只保存日期时间
        // 每次日历控件被保存到日期集合后，本集合清空
        this.dateNoCheckList = new ArrayList();

		// 当前日历控件设置后的日期保存集合
		this.dateList = new ArrayList();

		// 日历控件的唯一标识，因为一个页面可能存在多个日历控件
		this.uuid_pid = "date-"+generateUUID();

		// 默认配置
		this._defaultOptions = {
			width : '1000px',
			height : '597px',
			language : 'CH', // 语言
			showLunarCalendar : true, // 阴历
			showHoliday : true, // 休假
			showFestival : true, // 节日
			showLunarFestival : true, // 农历节日
			showSolarTerm : true, // 节气
			showMark : true, // 标记
			timeRange : {
				startYear : new Date().getFullYear(),
				endYear : new Date().getFullYear() + 1
			},
			timeZone : "", // 时区
			mark : {

			},
			theme : {
				changeAble : false,
				weeks : {
					backgroundColor : '#FBEC9C',
					fontColor : '#4A4A4A',
					fontSize : '20px'
				},
				days : {
					backgroundColor : '#ffffff',
					fontColor : '#565555',
					fontSize : '24px'
				},
				todaycolor : 'orange',
				activeSelectColor : 'orange',
				invalidDays : '#C1C0C0'
			},
			// 自定义参数，日期下方展示的文字格式
            formatStr:"",
			// 自定义参数，日历右侧需要进行填写的参数
			inputArr:[],
            // 自定义参数，用来控制时间是否可以选择，及是否可以选择之前的日期
			isReadOnly:false,
			// 自定义参数，日历控件的标题
            showTitle:"价格/库存设置"
		};

		// 容器
		this.container = document.querySelector(query);

		this._defaultOptions.width = this.container.style.offsetWidth;
		this._defaultOptions.height = this.container.style.offsetHeight;

		// this._options = Object.assign({}, this._defaultOptions, options);

		// 得到最终配置
		this._options = this.optionAssign(this._defaultOptions, options);

		this.create();
	}

	// 用B更新A的属性 并返回结果

	_createClass(
			SimpleCalendar,
			[
					{
						key : 'optionAssign',
						value : function optionAssign(optionsA, optionsB) {
							for ( var key in optionsB) {
								if (_typeof(optionsA[key]) !== 'object')
									optionsA[key] = optionsB[key];
								else {
									optionsA[key] = this.optionAssign(
											optionsA[key], optionsB[key]);
								}
							}
							return optionsA;
						}

					// 生成日历样式

					},
					{
						key : 'create',
						value : function create() {

                            var rootObject = this;

							var root = this.container;
							
							var rootThis = root;

							var uuid_pid = this.uuid_pid;
							
							root.setAttribute("date-pid", uuid_pid);
							
							root.innerHTML = '<div class="sc-select"> </div> <div class="sc-inputs"> </div> <div class="sc-header"> </div> <div class="sc-body"> </div> <div class="sc-none"> </div> ';
							
							if(this._options.width == null || this._options.width == undefined || this._options.width == ""){
								this._options.width = '1000px';
							}else{
								// 最小值
								this._options.width = parseInt(this._options.width.replaceAll("px","")) < 1000 ? '1000px' : parseInt(this._options.width.replaceAll("px","")) + "px";
							}
							
							if(this._options.height == null || this._options.height == undefined || this._options.height == ""){
								this._options.height = '597px';
							}else{
								// 最小值
								this._options.height = parseInt(this._options.height.replaceAll("px","")) < 597 ? '597px' : parseInt(this._options.height.replaceAll("px","")) + "px";
							}
							
							root.style.width = this._options.width;
							root.style.height = this._options.height;
							root.className = 'sc-calendar';
							var select = root.querySelector('.sc-select');
							var header = root.querySelector('.sc-header');
							var scbody = root.querySelector('.sc-body');
							var inputs = root.querySelector('.sc-inputs');

							select.setAttribute("date-pid", uuid_pid);
							header.setAttribute("date-pid", uuid_pid);
							scbody.setAttribute("date-pid", uuid_pid);
							inputs.setAttribute("date-pid", uuid_pid);
							
							// actions
							
							// 读取配置的input集合
							var inputArr = this._options.inputArr;
							
							if(inputArr.length > 0){
								
								select.style.width = "65%";
								header.style.width = "65%";
								scbody.style.width = "65%";
								inputs.style.width = "34.2%";
								inputs.style.display = "block";

								var inputs_html = '<div data-am-widget="titlebar" class="am-titlebar am-titlebar-default "><h2 class="am-titlebar-title" style="color:#3bb4f2;">' +this._options.showTitle+ '</h2></div>';
								
								inputs_html += "<table date-pid='"+uuid_pid+"' class='am-table' style='width:95%;margin-left:0%;'>";
								
								for(var input_index in inputArr){
									
									var input_obj = inputArr[input_index];
									
									// 设置当前文本在日历中显示
									if(eval(input_obj.isDateShow)){
										this.showInputArr[this.showInputArr.length] = input_obj;
										allShowInputArray[allShowInputArray.length] = input_obj;
									}

									var mark = input_obj.mark == undefined ? '' : input_obj.mark;

									var isRequired = input_obj.isRequired == undefined ? false : input_obj.isRequired;

                                    var isReadOnly = input_obj.isReadOnly == undefined ? false : input_obj.isReadOnly;

									var isType = input_obj.isType == undefined ? "number" : input_obj.isType;

									inputs_html += "<tr>";
									inputs_html += "<td date-pid='"+uuid_pid+"' style='width:50%;text-align:right;'>" + input_obj.name + "：</td>";
									inputs_html += "<td date-pid='"+uuid_pid+"' style='width:50%;'>";
									if(isReadOnly){
                                        inputs_html += "<input readonly type='"+isType+"' maxlength='9' date-pid='"+uuid_pid+"' class='am-form-field' placeholder='"+ mark +"' style='width:100%;' id='" + input_obj.id + "' name='" + input_obj.id + "'/>";
									}else{
                                        if(isRequired){
                                            //inputs_html += "<input type='"+isType+"' date-pid='"+uuid_pid+"' required class='am-form-field' placeholder='"+ mark +"' style='width:100%;' id='" + input_obj.id + "' name='" + input_obj.id + "'/>";
                                            inputs_html += "<input type='"+isType+"' maxlength='9' class='simple-date-input' date-pid='"+uuid_pid+"' isCheckDate='1' class='am-form-field' placeholder='"+ mark +"' style='width:100%;' id='" + input_obj.id + "' name='" + input_obj.id + "'/>";
                                        }else{
                                            inputs_html += "<input type='"+isType+"' maxlength='9' class='simple-date-input' date-pid='"+uuid_pid+"' isCheckDate='0' class='am-form-field' placeholder='"+ mark +"' style='width:100%;' id='" + input_obj.id + "' name='" + input_obj.id + "'/>";
                                        }
									}

									inputs_html += "</td></tr>";
								}

								inputs_html += "<tr></tr>";

								inputs_html += "<tr>";
								inputs_html += '<td><button type="button" date-pid="'+uuid_pid+'" class="am-btn am-btn-secondary bkyy-set-btn" onclick="">设置团期</button></td>';
								inputs_html += '<td><button type="button" date-pid="'+uuid_pid+'" class="am-btn am-btn-secondary bkyy-reset-date-btn">重置</button></td>';
								inputs_html += "</tr>";
								
								inputs_html += "</table>";
								
								inputs.innerHTML = inputs_html;
							}else{
								
								select.style.width = "100%";
								header.style.width = "100%";
								scbody.style.width = "100%";
								inputs.style.width = "0%";
								inputs.style.display = "none";
							}

							/*if(this._options.isReadOnly){
                                select.innerHTML = select.innerHTML + ''
                                    + '<label class="am-radio-inline"><input disabled type="radio" name="select-date-radio" date-type="0" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>天天发团</label>'
                                    + '<label class="am-radio-inline"><input disabled type="radio" name="select-date-radio" date-type="1" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>仅单日</label>'
                                    + '<label class="am-radio-inline"><input disabled type="radio" name="select-date-radio" date-type="2" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>仅双日</label>';
							}else{
                                select.innerHTML = select.innerHTML + ''
                                    + '<label class="am-radio-inline"><input type="radio" name="select-date-radio" date-type="0" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>天天发团</label>'
                                    + '<label class="am-radio-inline"><input type="radio" name="select-date-radio" date-type="1" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>仅单日</label>'
                                    + '<label class="am-radio-inline"><input type="radio" name="select-date-radio" date-type="2" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>仅双日</label>';
							}*/

                            select.innerHTML = select.innerHTML + ''
                                + '<label class="am-radio-inline"><input type="radio" name="select-date-radio" date-type="0" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>天天发团</label>'
                                + '<label class="am-radio-inline"><input type="radio" name="select-date-radio" date-type="1" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>仅单日</label>'
                                + '<label class="am-radio-inline"><input type="radio" name="select-date-radio" date-type="2" date-uuid-pid="'+uuid_pid+'" data-am-ucheck>仅双日</label>';

							header.innerHTML = header.innerHTML
									+ '<div class="sc-actions" date-pid="'+uuid_pid+'">'
									+ '      <div class="sc-yleft sc-left-div" date-pid="'+uuid_pid+'"><label class="am-icon-angle-left"></label></div>'
									+ '      <select date-pid="'+uuid_pid+'" class="sc-select-year sc-center-select" name="">'
									+ '      </select>'
									+ '      <div class="sc-yright sc-right-div" date-pid="'+uuid_pid+'"><label class="am-icon-angle-right"></label></div>'
									+ '  </div>';
							header.innerHTML = header.innerHTML
									+ '<div class="sc-actions" date-pid="'+uuid_pid+'">'
									+ '    <div class="sc-mleft sc-left-div" date-pid="'+uuid_pid+'"><label class="am-icon-angle-left"></label></div>'
									+ '    <select class="sc-select-month sc-center-select" name="" date-pid="'+uuid_pid+'">'
									+ '    </select>'
									+ '    <div class="sc-mright sc-right-div" date-pid="'+uuid_pid+'"><label class="am-icon-angle-right"></label></div>'
									+ '</div>';
							/*header.innerHTML = header.innerHTML
									+ '<div class="sc-actions" date-pid="'+uuid_pid+'"><span date-pid="'+uuid_pid+'" class="sc-return-today am-btn am-btn-link">返回今天</span></div>';
							header.innerHTML = header.innerHTML
									+ '<div class="sc-actions" date-pid="'+uuid_pid+'"><span class="sc-time"></span></div>';*/
							scbody.innerHTML = ' <div class="sc-week" date-pid="'+uuid_pid+'"> </div> <div class="sc-days" date-pid="'+uuid_pid+'"> </div>';
							var week = scbody.querySelector('.sc-week');
							var days = scbody.querySelector('.sc-days');
							for (var i = 0; i < 7; i++) {
								week.innerHTML = week.innerHTML + '<div class="sc-week-item" date-pid="'+uuid_pid+'"></div>';
							}
							for (var i = 0; i < 35; i++) {

								var addHtml = '<div class="sc-item" date-pid="'+uuid_pid+'" >';
								if(this._options.isReadOnly){
                                    addHtml += '<div class="select-day" date-pid="'+uuid_pid+'"><label date-pid="'+uuid_pid+'" class="am-checkbox"><input disabled date-pid="'+uuid_pid+'" type="checkbox" name="select-date-checkbox" class="checkDateInput" data-am-ucheck></label></div>';
								}else{
                                    addHtml += '<div class="select-day" date-pid="'+uuid_pid+'"><label date-pid="'+uuid_pid+'" class="am-checkbox"><input date-pid="'+uuid_pid+'" type="checkbox" name="select-date-checkbox" class="checkDateInput" data-am-ucheck></label></div>';
								}
                                addHtml += '<div class="day" date-pid="'+uuid_pid+'"></div>';
                                addHtml += '<div class="lunar-day"  date-pid="'+uuid_pid+'"></div>';
                                addHtml += '</div>';

                                days.innerHTML = days.innerHTML + addHtml;

                                if(this.showInputArr.length > 0){

                                    var this_showInputArr = this.showInputArr;

                                    var lunarDays = this.arrayfrom(this.container.querySelectorAll('.lunar-day'));

                                    lunarDays.forEach(function(v, i) {

                                        var inputs_html = "";

                                        for(var input_index in this_showInputArr){

                                            var input_obj = this_showInputArr[input_index];

                                            inputs_html += "<input type='hidden' date-pid='"+uuid_pid+"' class='"+input_obj.id+"-bkyy' style='width:90%;' value=''/>";
                                        }

                                        inputs_html += "<div class='lunar-day-html' date-pid='"+uuid_pid+"'></div>";

                                        v.innerHTML = inputs_html;
                                    });
                                }
								
							}
							// 添加下拉框数据
							this.updateSelect(this.tyear, this.tmonth);
							// 刷新日历
							this.update();
							// 时间刷新
							self.setInterval('SimpleCalendar.timeupdate()', 200);
						}

					// 刷新日历

					},
					{
						key : 'update',
						value : function update() {
							var month = arguments.length <= 0 || arguments[0] === undefined ? this.tmonth : arguments[0];
							var year = arguments.length <= 1 || arguments[1] === undefined ? this.tyear : arguments[1];

							this.updateSize();
							this.updateWeek();
							this.addData(year, month);
							this.updateHoliday(year, month);
							this.updateMark(year, month);
							this.updateFestival(year, month);
							this.updateEvent();
							this.updateTheme(this._options.theme);
							
							var root = this.container;
							
							var select = root.querySelector('.sc-select');
							var header = root.querySelector('.sc-header');
							var scbody = root.querySelector('.sc-body');
							var inputs = root.querySelector('.sc-inputs');

							// 重置背景高度及右边输入区域高度
							inputs.style.height = scbody.offsetHeight + header.offsetHeight + select.offsetHeight + "px";
							root.style.height = scbody.offsetHeight + header.offsetHeight + select.offsetHeight + 5 + "px";

							// 追加换行元素
							if($(root).next().attr("add-br") != "bkyy"){
								$(root).after("<br add-br='bkyy'/>");
							}

							// 初始化当前日历控件下的所有checkbox、radio为Amaza UI
                            $(root).find("input[type='checkbox'], input[type='radio']").each(function () {

                                $(this).uCheck();
                            });
						}

					// 调整大小

					},
					{
						key : 'updateSize',
						value : function updateSize() {
							var width = arguments.length <= 0
									|| arguments[0] === undefined ? this._options.width
									: arguments[0];
							var height = arguments.length <= 1
									|| arguments[1] === undefined ? this._options.height
									: arguments[1];

							// 将大小赋值给option
							this._options.width = width;
							this._options.height = height;

							this.container.style.width = width;
							this.container.style.height = height;

							// 根据长度和宽度大小调整适合的样式
							if (parseInt(width) < 500) {
								var actions = this.arrayfrom(this.container
										.querySelectorAll('.sc-actions'));
								actions.forEach(function(v, i) {
									v.classList.add('sc-actions-big');
								});
							} else {
								var actions = this.arrayfrom(this.container.querySelectorAll('.sc-actions'));
								actions.forEach(function(v, i) {
									v.classList.remove('sc-actions-big');
								});
							}
							if (parseInt(height) < 400) {
								var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
								var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
								items.forEach(function(v, i) {
									v.querySelector('.day').classList.add('sc-item-small');}
								);
								weeks.forEach(function(v, i) {
									v.classList.add('sc-item-small');
								});
							} else {
								var items = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
								var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
								items.forEach(function(v, i) {
									v.querySelector('.day').classList.remove('sc-item-small');
								});
								weeks.forEach(function(v, i) {
									v.classList.remove('sc-item-small');
								});
							}

						}

					// 刷新下拉框 只有在初始化和设置语言后才会更新

					},
					{
						key : 'updateSelect',
						value : function updateSelect(year, month) {
							// 下拉框
							var selectYear = this.container.querySelector('.sc-select-year');
							var selectMonth = this.container.querySelector('.sc-select-month');
							selectYear.innerHTML = '';
							for (var i = this._options.timeRange.startYear; i < this._options.timeRange.endYear + 1; i++) {
								selectYear.innerHTML += '<option value="' + i + '">' + i + '</option>';
							}
							selectMonth.innerHTML = '';
							for (var i = 0; i < 12; i++) {
								var data = this.languageData['months_' + this._options.language];
								selectMonth.innerHTML += '<option value="' + (i + 1) + '">' + data[i] + '</option>';
							}

							selectYear.value = year;
							selectMonth.value = month;
						}

					// 刷新星期

					},
					{
						key : 'updateWeek',
						value : function updateWeek() {
							var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
							var data = this.languageData['days_' + this._options.language];
							if (!data) {
								console.warn('language error!');
							}
							
							weeks.forEach(function(v, i) {
								
								var uuid_pid = v.getAttribute("date-pid");

								v.innerHTML = '<label date-pid="'+uuid_pid+'" class="am-checkbox"><input date-pid="'+uuid_pid+'" type="checkbox" id="week-radio-' + (i + 1) + '" name="week-radio-select" data-am-ucheck week-data="' + (i + 1) + '" />'+data[i]+'</label>';
								// 重新初始化radio对象
								$('#week-radio-' +  (i + 1) ).uCheck();
							});
						}

					// 添加阳历阴历数据

					},
					{
						key : 'addData',
						value : function addData(year, month) {
							var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
							var daysCheckBoxElement = this.arrayfrom(this.container.querySelectorAll('.checkDateInput'));
							
							var day = new Date(year, month - 1, 1);
							var week = day.getDay();
							if (week == 0)
								week = 7;
							
							// 计算得到第一个格子的日期
							var thispageStart = new Date(Date.parse(day) - (week - 1) * 24 * 3600 * 1000);
							
							// 获取需要展示的input
							var this_showInputArr = this.showInputArr;

							var uuid_pid = this.uuid_pid;

                            var no_html = "";

                            var formatNum = this._options.formatStr.getStringCount('<p>');

                            for(var i = 1; i <= formatNum; i++){
                                no_html += "<p>&nbsp;</p>";
                            }

                            var this_day = new Date();

							// 对每一个格子遍历
							for (var i = 0; i < 35; i++) {

								daysElement[i].className = 'sc-item';

								var theday = new Date(Date.parse(thispageStart) + i * 24 * 3600 * 1000);
								var writeyear = theday.getFullYear();
								var writeday = theday.getDate();
								var writemonth = theday.getMonth() + 1;
								if (writemonth != month) {
									daysElement[i].classList.add('sc-othermenth');
								}

								// 判断时间是否今天之前的
								if(theday < this_day){
                                    // 给外层添加过期样式
                                    daysElement[i].style.backgroundColor = "lightgray";
								}else{
                                    daysElement[i].style.backgroundColor = "white";
								}

								// 单独处理周天
								var writeweek = theday.getDay() == 0 ? 7 : theday.getDay();
								
								// 对每个格子进行id赋值  div标识-年-月-日
								daysElement[i].id = "sc-item-" + writeyear + "-" + writemonth + "-" + writeday;
								// 对每个格子进行自定义属性赋值  div标识-年-月-日
								daysElement[i].setAttribute("date-value", writeyear + "-" + writemonth + "-" + writeday);
								// 对每个日期的多选控件进行id赋值
								daysCheckBoxElement[i].id = uuid_pid + "-sc-checkbox-" + writeyear + "-" + writemonth + "-" + writeday;
								// 对每个日历控件的年数属性赋值
								daysCheckBoxElement[i].setAttribute("year-value", writeyear);
								// 对每个日历控件的月数属性赋值
								daysCheckBoxElement[i].setAttribute("month-value", writemonth);
								// 对每个日历控件的天数属性赋值
								daysCheckBoxElement[i].setAttribute("week-value", writeweek);
								// 对每个日历控件的天数属性赋值
								daysCheckBoxElement[i].setAttribute("days-value", writeday);
								// 默认全部不选中
								daysCheckBoxElement[i].checked = false;

								// 遍历获取所有的自定义输入框, 并对输入框的id进行赋值
								for(var input_index in this_showInputArr){
									
									var input_obj = this_showInputArr[input_index];
									
									// 自定义文本框集合
									var input_bkyy = this.arrayfrom(this.container.querySelectorAll('.' + input_obj.id + "-bkyy"));
									
									input_bkyy[i].id = input_obj.id + "-" + writeyear + "-" + writemonth + "-" + writeday;
									
									// input_bkyy[i].name = input_obj.id + "-" + writeyear + "-" + writemonth + "-" + writeday;

									input_bkyy[i].value = "";
								}
								
								daysElement[i].querySelector('.day').innerHTML = writeday;

								// 对每个时间进行自定义属性赋值  div标识-年-月-日
								daysElement[i].querySelector('.day').setAttribute("date-value", writeyear + "-" + writemonth + "-" + writeday);

                                daysElement[i].querySelector('.lunar-day').setAttribute("id", "lunar-day-" + writeyear + "-" + writemonth + "-" + writeday);

                                daysElement[i].querySelector('.lunar-day-html').setAttribute("id", uuid_pid + "-lunar-day-html-" + writeyear + "-" + writemonth + "-" + writeday);

                                // 将格式化字符串赋值
                                /*if(this._options.formatStr != null && this._options.formatStr != undefined && this._options.formatStr != ""){

                                    $("#lunar-day-html-" + writeyear + "-" + writemonth + "-" + writeday).html(no_html);
                                }*/

                                if(this._options.formatStr != null && this._options.formatStr != undefined && this._options.formatStr != ""){
                                	$("#" + uuid_pid + "-lunar-day-html-" + writeyear + "-" + writemonth + "-" + writeday).html(no_html);
                                }

								// 添加today样式
								if (this.tyear == writeyear && this.tday == writeday && this.tmonth == writemonth) {
									this.selectDay = daysElement[i];
									daysElement[i].classList.add("sc-today");
								}
							}
						}

					// 刷新标记日期

					},
					{
						key : 'updateMark',
						value : function updateMark(year, month) {
							var options = this._options;
							if (options.showMark) {
								var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
								var currentmonth = month - 1;
								// 取得节日数据
								var data = options.mark;
								if (data) {
									daysElement.forEach(function(v, i) {
										var day = + v.querySelector('.day').innerHTML;
										if (day == 1)
											currentmonth++;
										if (data[year + '-'+ currentmonth + '-'+ day]) {
											v.classList.add('sc-mark');
											v.title = data[year + '-'+ currentmonth+ '-' + day];
										} else {
											v.classList.remove('sc-mark');
											v.title = '';
										}
									});
								}
							}
						}

					// 刷新节日数据

					},
					{
						key : 'updateFestival',
						value : function updateFestival(year, month) {
							var options = this._options;
							var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
							var currentmonth = month - 1;
							// 取得节日数据
							var data = this.languageData['feativals_'+ this._options.language];
							var lunardata = this.languageData['lunarFeatival_'+ this._options.language];
							var solarTermdata = this.languageData['solarTerm'];
							if (!data) {
								console.warn('language error!');
							}
							daysElement.forEach(function(v, i) {
								var day = + v.querySelector('.day').innerHTML;
								if (day == 1)
									currentmonth++;

								// 24节气
								/*if (options.showSolarTerm) {
									if (solarTermdata[currentmonth + '-' + day]) {
										v.querySelector('.lunar-day').innerHTML = solarTermdata[currentmonth + '-' + day];
										v.classList.add('sc-festival');
									}
								}*/

								// 国际节日
								/*if (options.showFestival) {
									if (data[currentmonth + '-' + day]) {
										v.querySelector('.lunar-day').innerHTML = data[currentmonth
												+ '-' + day];
										v.classList.add('sc-festival');
									}
								}*/

								// 阴历节日
								/*if (lunardata && options.showLunarFestival) {
									var lunar = new LunarHelp(year, currentmonth, day).getLunarDayNum();
									if (lunardata[lunar.month + '-' + lunar.day]) {
										v.querySelector('.lunar-day').innerHTML = lunardata[lunar.month + '-' + lunar.day];
										v.classList.add('sc-festival');
									}
								}*/
							});
						}

					// 刷新假期 休假

					},
					{
						key : 'updateHoliday',
						value : function updateHoliday(year, month) {

							var options = this._options;
							/*if (options.showHoliday) {
								var daysElement = this.arrayfrom(this.container
										.querySelectorAll('.sc-item'));
								var currentmonth = month - 1;
								// 取得节日数据
								var data = this.languageData.vocation['data_'
										+ year];
								if (data) {
									daysElement
											.forEach(function(v, i) {
												var day = +v
														.querySelector('.day').innerHTML;
												if (day == 1)
													currentmonth++;
												// 国际节日
												if (data.indexOf(currentmonth
														+ '-' + day) > 0) {
													v.classList
															.add('sc-vocation');
												}
											});
								}
							}*/
						}

					// 刷新主题

					},
					{
						key : 'updateTheme',
						value : function updateTheme(theme) {
							if (this._options.theme.changeAble) {
								var daytheme = theme.days;
								var weektheme = theme.weeks;
								var weeks = this.arrayfrom(this.container.querySelectorAll('.sc-week-item'));
								var days = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
								weeks.forEach(function(v, i) {
									/*v.style.backgroundColor = weektheme.backgroundColor;
									v.style.fontSize = weektheme.fontSize;
									v.style.color = weektheme.fontColor;*/
								});
								days.forEach(function(v, i) {
									if (!v.classList.contains('sc-today')) {
										v.style.backgroundColor = daytheme.backgroundColor;
										v.querySelector('.day').style.color = daytheme.fontColor;
									} else {
										v.style.backgroundColor = theme.todaycolor;
									}
									v.querySelector('.day').style.fontSize = daytheme.fontSize;
								});
								var Calendar = this;
								// active border
								days.forEach(function(v, i) {
									v.onmouseover = function(e) {
										this.style.borderColor = theme.activeSelectColor;
										this.style.borderWidth = '1px';
									};
									v.onmouseout = function(e) {
										this.style.borderColor = '#F1EBE4';
										this.style.borderWidth = '0 0 1px 1px';
									};
								});
							}
						}

					// 刷新事件

					},
					{
						key : 'updateEvent',
						value : function updateEvent() {
							
							var rootDiv = this.container;
							var rootThis = this;
							
							var daysElement = this.arrayfrom(this.container.querySelectorAll('.sc-item'));
							var container = this.container;
							var calendar = this;
							daysElement.forEach(function(v, i) {
								v.onmouseover = function(e) {
									this.classList.add('sc-active-day');
								};
								v.onmouseout = function(e) {
									this.classList.remove('sc-active-day');
								};
							});

							// 给当前的日历批量选择绑定事件
							var setCheckAllBtns = this.arrayfrom(this.container.querySelectorAll('input[name=select-date-radio]'));
                            setCheckAllBtns.forEach(function(v, i) {
                                v.onclick = function () {

                                	// 获取当前是否选中
                                    if($(v).is(':checked')){

                                        dateTypeChange(rootThis, v, $(v).attr("date-uuid-pid"));
                                    }
                                }
                            });

                            // 给当前的日历批量绑定星期选择事件
                            var setWeekCheckAllBtns = this.arrayfrom(this.container.querySelectorAll('input[name=week-radio-select]'));
                            setWeekCheckAllBtns.forEach(function(v, i) {
                                v.onclick = function () {

                                    // 获取当前是否选中
                                    weekChange(rootThis, v, $(v).attr("date-pid"));
                                }
                            });

							// 给当前日历控件的日期选择按钮绑定事件
							var setCheckBtns = this.arrayfrom(this.container.querySelectorAll('input[name=select-date-checkbox]'));
                            setCheckBtns.forEach(function(v, i) {
                                v.onchange = function () {

									var days = $(v).attr("year-value") + "-" + $(v).attr("month-value") + "-" + $(v).attr("days-value");

									// 判断是选中还是未选中
                                    if($(v).is(':checked')){

                                        var removeIndex = -1;

                                        // 遍历删除日历集合查询是否存在并移除
                                        for(var j = 0; j < rootThis.dateNoCheckList.size(); j++){

                                            var item = rootThis.dateNoCheckList.get(j);

                                            // 如果存在，先删除原有的
                                            if(item == days){

                                                removeIndex = j;
                                                break;
                                            }else{
                                                continue;
                                            }
                                        }

                                        if(removeIndex != -1){
                                            rootThis.dateNoCheckList.remove(removeIndex);
                                        }

                                        // 将当前日期加入新增集合中
                                        rootThis.dateCheckList.add(days);
                                    }else{

                                    	if(!rootThis._options.isReadOnly){

											var removeIndex = -1;

											// 遍历选中日历集合查询是否存在并移除
											for(var j = 0; j < rootThis.dateCheckList.size(); j++){

												var item = rootThis.dateCheckList.get(j);

												// 如果存在，先删除原有的
												if(item == days){

													removeIndex = j;
													break;
												}else{
													continue;
												}
											}

											if(removeIndex != -1){
												rootThis.dateCheckList.remove(removeIndex);
											}

											// 将当前日期加入删除集合中
											rootThis.dateNoCheckList.add(days);
                                        }
									}
                                }
                            });

                            var setBtns = this.arrayfrom(this.container.querySelectorAll('.bkyy-set-btn'));
                            setBtns.forEach(function(v, i) {
                                v.onclick = function() {

									// 保存当前页面数据
                                    saveDatePageData(rootThis, this.getAttribute("date-pid"), 1);
                                    // 回填数据
									// refreshDate(rootThis, this.getAttribute("date-pid"));
                                }
                            });

                            var resetBtns = this.arrayfrom(this.container.querySelectorAll('.bkyy-reset-date-btn'));
                            resetBtns.forEach(function(v, i) {
                                v.onclick = function() {

                                    /*// 将解析后的日期放入日历控件
                                    rootThis.dateList = new ArrayList();

                                    // 获取当前月份的日期选择控件
                                    var dateCheckArray = document.getElementsByName("select-date-checkbox");

                                    var no_html = "";

                                    var formatNum = rootThis._options.formatStr.getStringCount('<p>');

                                    for(var i = 1; i <= formatNum; i++){
                                        no_html += "<p>&nbsp;</p>";
                                    }

                                    // 获取当前时间对象
                                    var today = new Date();

                                    for (var i = 0; i < dateCheckArray.length; i++) {

                                        if(dateCheckArray[i].getAttribute("date-pid") != this.getAttribute("date-pid")) {
                                            continue;
                                        }

                                        var save_year = dateCheckArray[i].getAttribute("year-value");
                                        var save_month = dateCheckArray[i].getAttribute("month-value");
                                        var save_days = dateCheckArray[i].getAttribute("days-value");
                                        var save_date = save_year + "-" + save_month + "-" + save_days;

                                        var checkDays = new Date(save_date.replace(/-/g,"/"));

                                        // 选中日期小于今天
                                        if(checkDays < today){
                                            console.warn("选中日期" + save_date + "小于今天");
                                            continue;
                                        }

                                        $(dateCheckArray[i]).uCheck("uncheck");

                                        // 将格式化字符串赋值
                                        $("#" + this.getAttribute("date-pid") + "-lunar-day-html-" + save_year + "-" + save_month + "-" + save_days).html(no_html);
                                    }

                                    // 重置所有文本框
*/
                                }
                            });

							var days_div = this.arrayfrom(this.container.querySelectorAll('.day'));
							days_div.forEach(function(v, i) {
								v.onclick = function() {
									/*calendar.selectDay = v;
									var pre = container.querySelector('.sc-selected');
									if (pre){
										pre.classList.remove('sc-selected');
									}
									
									// 获取当前选中的日期
									var getdate = this.getAttribute("date-value");
									
									// checkbox插件
									var checkboxElement = document.getElementById("sc-checkbox-" + getdate);
									
									// 选中当前日期
									if(checkboxElement.checked){
										checkboxElement.checked = false;
									}else{
										checkboxElement.checked = true;
									}
									
									// 更新日历集合中的日期信息
									
									this.classList.add('sc-selected');*/
								};
							});

							/*// 点击事件
							 * v.onclick = function() {
								calendar.selectDay = v;
								var pre = container.querySelector('.sc-selected');
								if (pre){
									pre.classList.remove('sc-selected');
								}
								
								// 获取当前选中的日期
								var getdate = this.getAttribute("date-value");
								
								// checkbox插件
								var checkboxElement = document.getElementById("sc-checkbox-" + getdate);
								
								// 选中当前日期
								if(checkboxElement.checked){
									checkboxElement.checked = false;
								}else{
									checkboxElement.checked = true;
								}
								
								// 更新日历集合中的日期信息
								
								this.classList.add('sc-selected');
							};
							*/
							
							
							var selectYear = container.querySelector('.sc-select-year');
							var selectMonth = container.querySelector('.sc-select-month');
							selectYear.onchange = function() {
                                // 保存当前页面数据
                                //saveDatePageData(rootThis, this.getAttribute("date-pid"));
								var m = selectMonth.value;
								var y = this.value;
								calendar.update(m, y);
                                // 刷新日历
                                refreshDate(rootThis, this.getAttribute("date-pid"));
							};

							selectMonth.onchange = function() {
                                // 保存当前页面数据
                                //saveDatePageData(rootThis, this.getAttribute("date-pid"));
								var y = selectYear.value;
								var m = this.value;
								calendar.update(m, y);
                                // 刷新日历
                                refreshDate(rootThis, this.getAttribute("date-pid"));
							};

							var yearadd = container.querySelector('.sc-yright');
							var yearsub = container.querySelector('.sc-yleft');
							var monthadd = container.querySelector('.sc-mright');
							var monthsub = container.querySelector('.sc-mleft');

							var s_year = this._options.timeRange.startYear;
							var e_year = this._options.timeRange.endYear;
							
							yearadd.onclick = function() {
                                // 保存当前页面数据
                                // saveDatePageData(rootThis, this.getAttribute("date-pid"));
								var currentyear = selectYear.value;
								if (currentyear < e_year){
									currentyear = parseInt(currentyear) + 1;
								}
								selectYear.value = currentyear;
								calendar.update(this.tmonth, currentyear);
                                // 刷新日历
                                refreshDate(rootThis, this.getAttribute("date-pid"));
							};
							yearsub.onclick = function() {
                                // 保存当前页面数据
                                // saveDatePageData(rootThis, this.getAttribute("date-pid"));
								var currentyear = selectYear.value;
								if (currentyear > s_year){
									currentyear = parseInt(currentyear) - 1;
								}
								selectYear.value = currentyear;
								calendar.update(this.tmonth, currentyear);
                                // 刷新日历
                                refreshDate(rootThis, this.getAttribute("date-pid"));
							};
							monthadd.onclick = function() {
								// 保存当前页面数据
								// saveDatePageData(rootThis, this.getAttribute("date-pid"));
								var currentmonth = selectMonth.value;
								var currentyear = selectYear.value;
								if (currentmonth < 12)
									currentmonth++;
								else {
									currentmonth = 1;
									if(parseInt(currentyear) == e_year){
										selectYear.value = currentyear;
									}else{
										selectYear.value = ++currentyear;
									}
								}
								;
								selectMonth.value = currentmonth;
								calendar.update(currentmonth, currentyear);
								// 刷新日历
								refreshDate(rootThis, this.getAttribute("date-pid"));
							};
							monthsub.onclick = function() {
								// 保存当前页面数据
								// saveDatePageData(rootThis, this.getAttribute("date-pid"));
								var currentmonth = selectMonth.value;
								var currentyear = selectYear.value;
								if (currentmonth > 1)
									currentmonth--;
								else {
									currentmonth = 12;
									if(parseInt(currentyear) == s_year){
										selectYear.value = currentyear;
									}else{
										selectYear.value = --currentyear;
									}
								}
								selectMonth.value = currentmonth;
								calendar.update(currentmonth, currentyear);
								// 刷新日历
								refreshDate(rootThis, this.getAttribute("date-pid"));
							};

							/*var returntoday = container.querySelector('.sc-return-today');
							returntoday.onclick = function() {
								selectYear.value = calendar.tyear;
								selectMonth.value = calendar.tmonth;
								calendar.update();
							};*/
						}

					// 添加标记

					},
					{
						key : 'addMark',
						value : function addMark(day, info) {
							this._options.mark[day] = info;
							this.update();
						}

					// 获取用户点击的日期

					},
					{
						key : 'getSelectedDay',
						value : function getSelectedDay() {
							var selectYear = this.container
									.querySelector('.sc-select-year').value;
							var selectMonth = this.container
									.querySelector('.sc-select-month').value;
							var selectDay = this.selectDay
									.querySelector('.day').innerHTML;
							return new Date(selectYear, selectMonth - 1,
									selectDay);
						}

					// 设置语言

					},
					{
						key : 'setLenguage',
						value : function setLenguage(language) {
							this._options.language = language;
							var selectYear = this.container
									.querySelector('.sc-select-year');
							var selectMonth = this.container
									.querySelector('.sc-select-month');
							this.updateSelect(selectYear.value,
									selectMonth.value);
							this.update();
						}

					// 设置是否显示节日

					}, {
						key : 'showFestival',
						value : function showFestival(s) {
							this._options.showFestival = s;
							this.update();
						}

					// 设置是否显示假期

					}, {
						key : 'showHoliday',
						value : function showHoliday(s) {
							this._options.showHoliday = s;
							this.update();
						}

					// 设置是否显示节气

					}, {
						key : 'showSolarTerm',
						value : function showSolarTerm(s) {
							this._options.showSolarTerm = s;
							this.update();
						}

					// 设置是否显示阴历节日

					}, {
						key : 'showLunarFestival',
						value : function showLunarFestival(s) {
							this._options.showLunarFestival = s;
							this.update();
						}

					// 设置是否显示阴历日期

					}, {
						key : 'showLunarCalendar',
						value : function showLunarCalendar(s) {
							this._options.showLunarCalendar = s;
							this.update();
						}

					// 设置是否显示标记日期

					}, {
						key : 'showMark',
						value : function showMark(s) {
							this._options.showMark = s;
							this.update();
						}
					// 将nodelist转为数组

					// nodelist转数组

					}, {
						key : 'arrayfrom',
						value : function arrayfrom(nidelist) {
							var array = [];
							[].forEach.call(nidelist, function(v) {
								array.push(v);
							});
							return array;
						}

					// get options() {
					// console.log(this._options);
					// }

					} ]);

	return SimpleCalendar;
}();
// 时间刷新函数

SimpleCalendar.timeupdate = function() {
	var timespan = document.querySelectorAll('.sc-time');
	var now = new Date();
	var nh = now.getHours();
	var nm = now.getMinutes();
	var ns = now.getSeconds();
	if (nh < 10)
		nh = '0' + nh;
	if (nm < 10)
		nm = '0' + nm;
	if (ns < 10)
		ns = '0' + ns;
	[].forEach.call(timespan, function(v) {
		v.innerHTML = '时间：' + nh + ":" + nm + ':' + ns;
	});
};
// 国际化，和一些节日数据，标记数据
SimpleCalendar.prototype.languageData = {
	feativals_CH : {
		'1-1' : '元旦',
		'2-14' : '情人节',
		'3-8' : '妇女节',
		'3-12' : '植树节',
		'4-1' : '愚人节',
		'4-22' : '地球日',
		'5-1' : '劳动节',
		'5-4' : '青年节',
		'6-1' : '儿童节',
		'7-1' : '建党节',
		'8-1' : '建军节',
		'9-10' : '教师节',
		'10-1' : '国庆节',
		'12-25' : '圣诞节'
	},
	feativals_EN : {
		'1-1' : "new year’s day",
		'2-14' : "Saint Valentine's Day",
		'3-8' : 'international women’s day',
		'3-12' : "Arbor Day",
		'4-1' : "April Fool's Day",
		'4-22' : "Earth Day",
		'5-1' : "international labour day",
		'5-4' : "Chinese Youth Day",
		'6-1' : "Children's Day",
		'7-1' : "The party's Day",
		'8-1' : "the Army's Day",
		'9-10' : "Teachers' Day",
		'10-1' : 'National Day',
		'12-25' : 'Christmas Day'
	},
	lunarFeatival_CH : {
		'1-1' : '春节',
		'2-2' : '龙抬头',
		'1-15' : '元宵节',
		'4-4' : '寒食节',
		'4-5' : '清明节',
		'5-5' : '端午节',
		'8-15' : '中秋节',
		'9-9' : '重阳节',
		'12-30' : '除夕'
	},
	// 节气
	solarTerm : {
		'2-3' : '立春',
		'5-5' : '立夏',
		'8-7' : '立秋',
		'11-7' : '立冬',
		'2-18' : '雨水',
		'5-20' : '小满',
		'8-22' : '处暑',
		'11-22' : '小雪',
		'3-5' : '惊蛰',
		'6-5' : '芒种',
		'9-7' : '白露',
		'12-6' : '大雪',
		'3-20' : '春分',
		'6-21' : '夏至',
		'9-22' : '秋分',
		'12-21' : '冬至',
		'4-4' : '清明',
		'7-6' : '小暑',
		'10-8' : '寒露',
		'1-5' : '小寒',
		'4-19' : '谷雨',
		'7-22' : '大暑',
		'10-23' : '霜降',
		'1-20' : '大寒'

	},
	days_EN : [ "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" ],
	days_CH : [ "一", "二", "三", "四", "五", "六", "日" ],
	months_EN : [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec" ],
	months_CH : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月",
			"十一月", "十二月" ],
	vocation : {
		data_2016 : [ '1-1', '1-2', '1-3', '2-7', '2-8', '2-9', '2-10', '2-11',
				'2-12', '2-13', '4-2', '4-3', '4-4', '4-30', '5-1', '5-2',
				'6-9', '6-10', '6-11', '9-15', '9-16', '9-17', , '10-1',
				'10-2', '10-3', '10-4', '10-5', '10-6', '10-7' ]
	}
};

SimpleCalendar.prototype.tyear = new Date().getFullYear();
SimpleCalendar.prototype.tmonth = new Date().getMonth() + 1;
SimpleCalendar.prototype.tday = new Date().getDate();

// 给每个日期的选择按钮绑定事件
// 批量设置根据星期选中
var weekChange = function(rootThis, o, pid){

	var weekValue = o.getAttribute("week-data");

	var dateCheckArray = document.getElementsByName("select-date-checkbox");

	for (var i = 0; i < dateCheckArray.length; i++) {
		
		if(dateCheckArray[i].getAttribute("week-value") == weekValue){

            if(dateCheckArray[i].getAttribute("date-pid") != pid) {

                continue;
            }

			if(dateCheckArray[i].disabled){
				
				continue;
			}

			if(o.checked){

                $(dateCheckArray[i]).uCheck('check');
			}else{

                $(dateCheckArray[i]).uCheck('uncheck');
			}
		}
	}
}

//批量设置根据类型选中
var dateTypeChange = function(rootThis, o, pid){

	// 发团类型
	var dateType = o.getAttribute("date-type");

	var dateCheckArray = document.getElementsByName("select-date-checkbox");

    for (var i = 0; i < dateCheckArray.length; i++) {

        if (dateCheckArray[i].getAttribute("date-pid") != pid) {
            continue;
        }

        $(dateCheckArray[i]).uCheck('uncheck');
    }

    //清空选中的按钮集合
    rootThis.dateCheckList = new ArrayList();
    //清空未选中的按钮集合
    rootThis.dateNoCheckList = new ArrayList();

	for (var i = 0; i < dateCheckArray.length; i++) {

		if(dateCheckArray[i].getAttribute("date-pid") != pid) {

			continue;
		}

        if(dateCheckArray[i].disabled){

            continue;
        }

		// 获取当前时间对象
		var today = new Date();

		// 选中的日期解析
		var save_year = dateCheckArray[i].getAttribute("year-value");
		var save_month = dateCheckArray[i].getAttribute("month-value");
		var save_days = dateCheckArray[i].getAttribute("days-value");
		var item_day = save_year + "-" + save_month + "-" + save_days;

        // 选中日期
        if(item_day != null && item_day != undefined && item_day != ""  && item_day != "--" ){

            var checkDays = new Date(item_day.replace(/-/g,"/"));

            // 选中日期小于今天
            if(checkDays < today){
                console.warn("选中日期" + item_day + "小于今天");
                continue;
            }
        }

		if(dateType == "0"){

            $(dateCheckArray[i]).uCheck('check');
		}else if(dateType == "1"){

			if(dateCheckArray[i].getAttribute("days-value") % 2 != 0){

                $(dateCheckArray[i]).uCheck('check');

			}else{

				continue;
			}
		}else if(dateType == "2"){

			if(dateCheckArray[i].getAttribute("days-value") % 2 == 0){

                $(dateCheckArray[i]).uCheck('check');
			}else{

                continue;
			}
		}
	}
}

/**
 * 保存当页日期数据
 */
var saveDatePageData = function(simpleDate, pid, option) {

    if(simpleDate.uuid_pid != pid){

        return;
    }

    var no_html = "";

    var formatNum = simpleDate._options.formatStr.getStringCount('<p>');

    for(var i = 1; i <= formatNum; i++){
        no_html += "<p>&nbsp;</p>";
    }

    var showInputErrorNumber = 0;

    // 然后通过必填字段进行遍历. 查看必填字段是否填写
    for(var input_index in allShowInputArray) {

        var input_obj = allShowInputArray[input_index];

        // 只读取当前日历控件的内容
        if ($("#" + input_obj.id).attr("date-pid") != simpleDate.uuid_pid) {
            continue;
        }

        // 判断是否必填的字段
        if($("#"+input_obj.id).attr("isCheckDate") == null || $("#"+input_obj.id).attr("isCheckDate") == undefined || $("#"+input_obj.id).attr("isCheckDate") == "0"){
            continue;
        }

        // 检查必填字段是否填写
        if($("#"+input_obj.id).val() == null || $("#"+input_obj.id).val() == undefined || $("#"+input_obj.id).val() == ""){
            showInputErrorNumber++;
            break;
        }
    }

    if(option != null && option != undefined && option != "" && option == 1){
        if(showInputErrorNumber > 0){
            bkyyAlert("请检查是否有必填设置没有输入 ?");
            return;
        }
	}

    // 遍历将本次未选中的日期集合同步总的日历集合做移除
    for(var j = 0; j < simpleDate.dateNoCheckList.size(); j++){

        // 取消的日期
        var item_day = simpleDate.dateNoCheckList.get(j);

        var removeIndex = -1;

        // 遍历日历集合，先查询原有的日期是否存在
        for(var k = 0; k < simpleDate.dateList.size(); k++){

            var item = simpleDate.dateList.get(k);

            // 如果存在，先删除原有的
            if(item.get("date") == item_day){

                removeIndex = k;
                break;
            }else{
                continue;
            }
        }

        // 先查询本次保存的日期，原有的集合中是否存在，如果存在，先删除掉
        if(removeIndex != -1){
            simpleDate.dateList.remove(removeIndex);

            // 取消自定义文本
            $("#" + pid + "-lunar-day-html-" + item_day).html(no_html);
        }
    }

	// 遍历将本次选中的日期集合同步到总的日历集合中
    for(var j = 0; j < simpleDate.dateCheckList.size(); j++){

        // 日历中展示的字符串HTML
        var format_string_html = simpleDate._options.formatStr;

        var item_day = simpleDate.dateCheckList.get(j);

        // 获取当前时间对象
        var today = new Date();

        // 选中日期
        if(item_day != null && item_day != undefined && item_day != ""  && item_day != "--" ){

            var checkDays = new Date(item_day.replace(/-/g,"/"));

            // 选中日期小于今天
            if(checkDays < today){
                console.warn("选中日期" + item_day + "小于今天");
                continue;
            }
        }

        // 获取当前月份的日期选择控件
        var dateCheckArray = document.getElementsByName("select-date-checkbox");

        var exitsNumber = 0;  // 当前添加的日期是否在本页面存在

        // 查询本次设置的时间，在当前月份中是否存在
        for (var m = 0; m < dateCheckArray.length; m++) {

            if (dateCheckArray[m].getAttribute("date-pid") != pid) {
                continue;
            }

            var page_save_year = dateCheckArray[m].getAttribute("year-value");
            var page_save_month = dateCheckArray[m].getAttribute("month-value");
            var page_save_days = dateCheckArray[m].getAttribute("days-value");
            var page_save_date = page_save_year + "-" + page_save_month + "-" + page_save_days;

            if(new Date(item_day.replace(/-/g,"/")).getTime() == new Date(page_save_date.replace(/-/g,"/")).getTime()){
                exitsNumber++;
			}else{
            	continue;
			}
			// 如果存在，还要看是否选中状态
			if($(dateCheckArray[m]).is(':checked')){ // 选中
                exitsNumber++
				break;
			}else{
                exitsNumber = 0;
			}
        }

        // 不存在的话，不用设置他了
        if(exitsNumber == 0){
			continue;
		}

        // 初始化时间对象
        var dateMap = new Map();
        // 截取天数中的年份
        var _year = item_day.substring(0, item_day.indexOf("-"));
        // 截取天数中的月份
        var _month = item_day.substring(item_day.indexOf("-") + 1, item_day.lastIndexOf("-"));
        // 截取天数中的天数
        var _days = item_day.substring(item_day.lastIndexOf("-") + 1, item_day.length);

        dateMap.put("year", _year);
        dateMap.put("month", _month);
        dateMap.put("days", _days);
        dateMap.put("date", item_day);

        var format_string_html = simpleDate._options.formatStr;

        /*var isCheckDateNumber = 0;*/

        // 获取自定义的字段
        // 遍历自定义字段
        for(var input_index in allShowInputArray){

            var input_obj = allShowInputArray[input_index];

            // 只读取当前日历控件的内容
            if($("#" + input_obj.id).attr("date-pid") != pid){

                continue;
            }

            /*// 判断必填项是否填写
            // 判断是否必填的字段
            if($("#"+input_obj.id).attr("isCheckDate") != null &&
               $("#"+input_obj.id).attr("isCheckDate") != undefined &&
               $("#"+input_obj.id).attr("isCheckDate") != "" &&
               $("#"+input_obj.id).attr("isCheckDate") == "1"){

                // 检查必填字段是否填写
                if($("#"+input_obj.id).val() == null || $("#"+input_obj.id).val() == undefined || $("#"+input_obj.id).val() == ""){
                    isCheckDateNumber++;
                    break;
                }
			}*/

            var input_id = input_obj.id + "-" + item_day;

            // 将总的文本输入到每个日期的文本中
            $("#"+input_id).val($("#" + input_obj.id).val());

            var input_val = $("#"+input_id).val();

            input_val = input_val == null || input_val == undefined ? "" : input_val;

            dateMap.put(input_obj.id, input_val);

            // if(format_string_html != null && format_string_html != undefined && format_string_html != "") {
                if(input_val == null || input_val == undefined || input_val == ""){
                    format_string_html = format_string_html.replaceAll(input_obj.id, "&nbsp;");
                }else{
                    format_string_html = format_string_html.replaceAll(input_obj.id, input_val);
                }
            // }
        }

        // 将格式化字符串赋值
        if(simpleDate._options.formatStr != null && simpleDate._options.formatStr != undefined && simpleDate._options.formatStr != ""){

            if(format_string_html.indexOf("&nbsp;") == -1){
                $("#" + pid + "-lunar-day-html-" + _year + "-" + _month + "-" + _days).html(format_string_html);
			}
        }

        var removeIndex = -1;

        // 遍历日历集合，先查询原有的日期是否存在
        for(var k = 0; k < simpleDate.dateList.size(); k++){

            var item = simpleDate.dateList.get(k);

            // 如果存在，先删除原有的
            if(item.get("date") == item_day){

                removeIndex = k;
                break;
            }else{
                continue;
            }
        }

        // 先查询本次保存的日期，原有的集合中是否存在，如果存在，先删除掉
        if(removeIndex != -1){
            simpleDate.dateList.remove(removeIndex);
        }

        simpleDate.dateList.add(dateMap);
    }

    // 本页所有的选项卡复位
    var dateCheckArray = document.getElementsByName("select-date-checkbox");
    for (var i = 0; i < dateCheckArray.length; i++) {

        if(dateCheckArray[i].getAttribute("date-pid") != pid) {
            continue;
        }

        $(dateCheckArray[i]).uCheck('uncheck');
    }

    // 还原选中的全选按钮
    var radioCheckArray = document.getElementsByName("select-date-radio");
    for (var i = 0; i < radioCheckArray.length; i++) {
        $(radioCheckArray[i]).uCheck('uncheck');
    }

    // 还原所有星期选项卡
    var radioWeekCheckArray = document.getElementsByName("week-radio-select");
    for (var i = 0; i < radioWeekCheckArray.length; i++) {
        $(radioWeekCheckArray[i]).uCheck('uncheck');
    }

	//清空选中的按钮集合
    simpleDate.dateCheckList = new ArrayList();
    //清空未选中的按钮集合
    simpleDate.dateNoCheckList = new ArrayList();

	var deleteIndexArray = new ArrayList();

    // 将小于今天的时间全部下标找到
    for (var int = 0; int < simpleDate.dateList.size(); int++) {

        // 获取日期对象
        var item = simpleDate.dateList.get(int);

        var item_day = item.get("date");

        // 选中日期
        if(item_day != null && item_day != undefined && item_day != ""  && item_day != "--" ){

            var checkDays = new Date(item_day.replace(/-/g,"/"));

            // 选中日期小于今天
            if(checkDays < today){
                console.warn("选中日期" + item_day + "小于今天");
                deleteIndexArray.add(int);
                continue;
            }
        }
    }

    // 将小于今天的时间全部清除
    for (var int = 0; int < deleteIndexArray.size(); int++) {

        var index = simpleDate.dateList.get(int);

        simpleDate.dateList.remove(index);
    }

    console.log(simpleDate.dateList.toString());
}

// 检查是否有未填项
function checkDateList(simpleDate){

	// 首先查看是否有数据
	if(simpleDate.dateList.size() == 0){
		return false;
	}

    // 获取当前时间对象
    var today = new Date();

    var error_num = 0;

    var okDateList = new ArrayList();

	// 遍历当前的日期集合
	for (var int = 0; int < simpleDate.dateList.size(); int++) {

		// 获取日期对象
		var item = simpleDate.dateList.get(int);

		var item_day = item.get("date");

        // 选中日期
		if(item_day != null && item_day != undefined && item_day != "" && item_day != "--"){

			var checkDays = new Date(item_day.replace(/-/g,"/"));

            // 选中日期小于今天
            if(checkDays < today){
                console.warn("选中日期" + item_day + "小于今天");
                continue;
            }
		}

        error_num = 0;

        // 然后通过必填字段进行遍历，查看日历中是否有未填
        for(var input_index in allShowInputArray) {

            var input_obj = allShowInputArray[input_index];

            // 只读取当前日历控件的内容
            if ($("#" + input_obj.id).attr("date-pid") != simpleDate.uuid_pid) {
                continue;
            }

            // 判断是否必填的字段
            if($("#"+input_obj.id).attr("isCheckDate") == null || $("#"+input_obj.id).attr("isCheckDate") == undefined || $("#"+input_obj.id).attr("isCheckDate") == "0"){
                continue;
            }

			// 检查必填自定义字段的错误
			if(item.get(input_obj.id) == null || item.get(input_obj.id) == undefined || item.get(input_obj.id) == ""){

                console.warn("日期" + item_day + "价格设置错误");
                error_num++;
                break;
			}
        }

        // 没有问题的放在ok的集合中
		if(error_num == 0){
            okDateList.add(item);
			/*break;*/
		}
    }

    simpleDate.dateList = okDateList;
    /*if(error_num > 0){
        return false;
    }else{
    	return true;
	}*/

    return true;
}


// 回填日历数据
function refreshDate(simpleDate, pid){

	if(simpleDate.uuid_pid != pid){
		
		return;
	}

    // 获取当前时间对象
    var today = new Date();

	// 获取当前月份的日期选择控件
	var dateCheckArray = document.getElementsByName("select-date-checkbox");
	
	for (var i = 0; i < dateCheckArray.length; i++) {

        var format_string_html = simpleDate._options.formatStr;

		if(dateCheckArray[i].getAttribute("date-pid") != pid) {
			continue;
		}

        var save_year = dateCheckArray[i].getAttribute("year-value");
        var save_month = dateCheckArray[i].getAttribute("month-value");
        var save_days = dateCheckArray[i].getAttribute("days-value");
        var save_date = save_year + "-" + save_month + "-" + save_days;

        /*// 选中日期
        var checkDays = new Date(save_date.replace(/-/g,"/"));

        // 选中日期小于今天
        if(checkDays < today){
            console.warn("选中日期" + save_date + "小于今天");
            continue;
        }*/

		// 遍历已经保存的日历集合
		var query_map = new Map();
		
		for (var int = 0; int < simpleDate.dateList.size(); int++) {
			
			if(save_date == simpleDate.dateList.get(int).get("date")){
				
				query_map = simpleDate.dateList.get(int);
				break;
			}else{
				continue;
			}
		}

		// 如果找到当前对象
		if (query_map.size() > 0) {

            // 将当前的日期设置为选中
			// $(dateCheckArray[i]).uCheck("check");

            if(simpleDate._options.isReadOnly){

                // 将日期的选择框的disabled删除
                $("#" + pid + "-sc-checkbox-" + save_date).removeAttr("disabled");
            }

            /*var isCheckDateNumber = 0;*/

            // 遍历自定义字段
			// 将当前页面的数据回填
			for(var input_index in allShowInputArray){
				
				var input_obj = allShowInputArray[input_index];
				
				var input_id = input_obj.id + "-" + query_map.get("date");
				
				var input_value = query_map.get(input_obj.id);
				
				$("#"+input_id).val(input_value);

/*                // 判断必填项是否填写
                // 判断是否必填的字段
                if($("#"+input_obj.id).attr("isCheckDate") != null &&
                    $("#"+input_obj.id).attr("isCheckDate") != undefined &&
                    $("#"+input_obj.id).attr("isCheckDate") != "" &&
                    $("#"+input_obj.id).attr("isCheckDate") == "1"){

                    // 检查必填字段是否填写
                    if($("#"+input_obj.id).val() == null || $("#"+input_obj.id).val() == undefined || $("#"+input_obj.id).val() == ""){
                        isCheckDateNumber++;
                    }
                }*/

                if(input_value == null || input_value == undefined || input_value == ""){
                    format_string_html = format_string_html.replaceAll(input_obj.id, "&nbsp;");
                }else{
                    format_string_html = format_string_html.replaceAll(input_obj.id, input_value);
                }
			}

            // 将格式化字符串赋值
            if(format_string_html != null && format_string_html != undefined && format_string_html != ""){
				if(format_string_html.indexOf("&nbsp;") == -1){
                    $("#" + pid + "-lunar-day-html-" + save_year + "-" + save_month + "-" + save_days).html(format_string_html);
				}
            }
		}else{

			if(simpleDate._options.isReadOnly){

                $("#" + pid + "-sc-checkbox-" + save_date).attr("disabled",true);
			}
		}
	}
}