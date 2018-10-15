<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/base_index.jsp" %>
<style>
	.animated{
		border-bottom: 1px solid #ddd;
	}
	.btn-position{
		text-align: center;
		margin-top:15px;
	}
	td{
		height:30px;
		line-height: 30px;
		color: #333;
	}
	thead{
		background:#ececec;
	}
	.progress-div{
		height: 34px;
		background-color: #a2e8d9;
		position: absolute;
		top: 0;
		left: 0;
	}
</style>
<!DOCTYPE HTML>
<html>

<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<form class="form-horizontal m-t" id="addExp" name="addExp" enctype ="multipart/form-data">
                    <input type="hidden" name="xmlid" id="xmlid">
					<input type="hidden" name="blb" id="blb">
					<div class="form-group">
						<%--                          <label class="font-noraml">文件选择（多选）</label>--%>
						<label class="col-sm-3 control-label">选择Excel</label>
						<div class="col-sm-8">
							<%--                              <input type="file" id="file" name="file"/>--%>
								<input type='file' id='file_upload' name='file_upload'  style="width:70%;"  class="form-control" onchange="getXmlId()">
						</div>
					</div>

						<div  style="width:100%;">
							<div class="form-group" style="width:59%;margin:0 auto;position: relative;height: 34px;">
								<div class="progress-div" id="progress-div"></div>
								<%--                              <input type="file" id="file" name="file"/>--%>
								<input type='text' id='jdt' name='jdt' style="position: absolute;top: 0;left:0;background-color: transparent;border: none;" class="form-control" readonly="true">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 btn-position">
								<button id="expyl" class="btn btn-w-m btn-warning"  type="button">数据预览</button>
							</label>
							<label class="col-sm-4 btn-position">
								 <button  class="btn btn-w-m btn-primary"  type="button">清除数据</button>
							</label>
							<%--<label class="col-sm-3"></label>--%>
							<label class="col-sm-4  btn-position">
								<button id="expdr" class="btn btn-w-m btn-info" type="button">导入数据</button>
							</label>
						</div>

				</form>
			</div>
		</div>
	</div>
	<div>
		<table width="100%">
			<%--<thead>--%>
				<%--<tr>--%>
					<%--<td>姓名</td>--%>
					<%--<td>年龄</td>--%>
					<%--<td>姓名</td>--%>
					<%--<td>年龄</td>--%>
				<%--</tr>--%>
			<%--</thead>--%>
			<%--<tbody>--%>
				<%--<tr>--%>
					<%--<td>lily</td>--%>
					<%--<td>12</td>--%>
					<%--<td>name</td>--%>
					<%--<td>15</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td>lily</td>--%>
					<%--<td>12</td>--%>
					<%--<td>name</td>--%>
					<%--<td>15</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td>lily</td>--%>
					<%--<td>12</td>--%>
					<%--<td>name</td>--%>
					<%--<td>15</td>--%>
				<%--</tr>--%>
			<%--</tbody>--%>
		</table>
	</div>
	<%--<button class="clear-data">清除数据</button>--%>
</body>
<jsp:include page="../common/base_js.jsp"></jsp:include>
<script type="text/javascript">

function getXmlId(){
    var filePath = $('#file_upload').val();
    var fileName = '';
    fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
    if (fileName == '') {
        swal('请选择文件!');
        return false;
    }
   $.ajax({
	   type: 'post',
       async: false,
       url: 'getXmlId.do',
       data: {"fileName":fileName},
	   success:function(data){
            if(data.code == 1){
              $("#xmlid").val(data.xmlId);
               if(data.xmlBlb == 2){
                   $("#expyl").attr("disabled","disabled");
			   }else{
                   $("#expyl").removeAttrs("disabled");
			   }
			 }else{
              swal(data.massage);
			}
	   }
   })

}

$('#expdr').click(function(){
        $("#jdt").val("");
        $("#progress-div").css("width","0%");
		var filePath = $('#file_upload', $('#addExp')).val();
		var fileName = '';
		fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
		var suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
		var xmlid =$("#xmlid").val();
		var type = 'xls';
        var type2 = 'xlsx';
		if (fileName == '') {
            swal('请选择文件!');
            var obj=document.getElementById('file_upload');
            obj.outerHTML=obj.outerHTML;
			return false;
		}
		else if (suffix != type && suffix != type2) {
            swal('请选择EXCEL格式的文件导入！');
            var obj=document.getElementById('file_upload');
            obj.outerHTML=obj.outerHTML;
			return false;
		}
		else {
			var formData = new FormData($("#addExp")[0]);
            formData.append("excelId",xmlid);
			index();
            setTimeout('importSchedule()', 300);
			//importSchedule();
			$.ajax({
				type: 'post',
				async: true,
//                cache : false,
				url: 'dataImport.do',
				data: formData,
				cache: false,
                dataType:"json",
				contentType: false,
				processData: false,
				success: function (data) {
					if (data.flag == '1') {
                        $("#progress-div").css("width",data.jd+"%");
                           $("#jdt").val("导入进度："+data.jd+"%"+"             "+"明细："+data.mx);
						 layer.close(index());//加载完成后关闭旋转图标
						// swal("","数据导入成功","warning");
                        retrunMessage(data.totle,data.suces,data.error);
					} else {
                        layer.close(index())
                        retrunMessage(data.totle,data.suces,data.error);
                      //  swal("",data.message,"warning");
					}
				}
			})
		}
})
$('#expyl').click(function(){
    var filePath = $('#file_upload', $('#addExp')).val();
    //var fileName = '';
    fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
    var suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
    var xmlid =$("#xmlid").val();
    var type = 'xls';
    var type2 = 'xlsx';
    if (fileName == '') {
        swal('请选择文件!');
        var obj=document.getElementById('file_upload');
        obj.outerHTML=obj.outerHTML;
        return false;
    }
    else if (suffix != type && suffix != type2){
        swal('请选择EXCEL格式的文件导入！');
        var obj=document.getElementById('file_upload');
        obj.outerHTML=obj.outerHTML;
        return false;
    }
    else {
        index();
        var formData = new FormData($("#addExp")[0]);
        formData.append("excelId",xmlid);
        $.ajax({
            type: 'post',
            async: false,
            url: 'dataExpyl.do',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                layer.close(index());
                var str = "";
                str+="<thead>                                ";
                str+="				<tr>                     ";
                for(var tis =0;tis<data.title.length;tis++) {
                    str += "					<td>"+data.title[tis]+"</td>        ";
                }
                str+="				</tr>                    ";
                str+="			</thead>                     ";
                str+="			<tbody>                      ";
//                 console.log(data.title);
//                 console.log(data.names);
//                 console.log(data.datas);
                 for(var i =0;i<(data.datas).length;i++) {
                     var b = $.isEmptyObject(data.datas[i]);
                     if(!b) {
                         str += "				<tr>                     ";
                         for (var j = 0; j < data.names.length; j++) {
                             var gh = data.names[j];
                             // console.log((data.datas)[i][gh]);
							 if((data.datas)[i][gh]) {
                                 str += "					<td>" + (data.datas)[i][gh] + "</td>        ";
                             }else{
                                 str += "					<td>--</td>        ";
							 }
                         }
                         str += "				</tr>                    ";
                     }
                 }
                    str+="			</tbody>                     ";
                 $("table").html(str);
            }
        })
    }
})
$(".btn-primary").click(function () {
	$("table").html(" ");
//	$.ajax({
//        type : 'get',
//        async: false,
//        //访问此url,用来返回进度
//        url : basePath+"/test2",
//        success:function(data) {
//
//        }
//	});
})
//导入旋转
function index(){
    var index = layer.load(1, {//正在加载中的旋转图标，防止用户进行其它操作
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    return index;

}
/**/
//进度条定时查询方法
var importSchedule=function(){
    //定时访问进度
	//debugger;
    var int=setInterval(function(){
        $.ajax({
            type : 'post',
            async: true,
//            cache : false,
            //访问此url,用来返回进度
            url : "getSejd.do",
            success:function(data) {
                if(data ==""|| data == null){
                    clearInterval(int);
				}else if (data.jd == 100) {
                    //结束当前定时任务,
                    clearInterval(int);
                    //layer.close(index());//加载完成后关闭旋转图标
                }else {
                    $("#progress-div").css("width",data.jd+"%");
                    $("#jdt").val("导入进度："+data.jd+"%"+"             "+"明细："+data.mx);
                 //   console.log(data);
                 }
            }
        });
        //200毫秒调用一次
    },100);
}

//导入-输出显示反馈提示
function retrunMessage(totle,success,error){
    if(error=='0'){
        swal({
            title: "本次导入成功，共计"+success+"条!!!",
            type: "success",
            showCancelButton: false,
            closeOnConfirm: true,
            confirmButtonText: "确定",
            confirmButtonColor: "#1BB394"
        }, function() {
            window.close();
        });
    }else{
        swal({
            title: "本次共计"+totle+"条数据,成功导入"+success+"条数据,失败"+error+"条数据!!!",
            type: "success",
            showCancelButton: false,
            closeOnConfirm: true,
            confirmButtonText: "下载错误信息",
            confirmButtonColor: "red"
        }, function() {
            window.location.href=basePath+'errorInfoDownload.do';
        });
    }
}
</script>


</html>