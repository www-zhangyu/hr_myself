

/*��֤���п�*/
function luhmCheck(bankno){
		if (bankno.length < 16 || bankno.length > 19) {
			alert("���п��ų��ȱ�����16��19֮��");
			return false;
		}
		var num = /^\d*$/;  //ȫ����
		if (!num.exec(bankno)) {
			alert("���п��ű���ȫΪ����");
			return false;
		}
		//��ͷ6λ
		var strBin="10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";    
		if (strBin.indexOf(bankno.substring(0, 2))== -1) {
			alert("���п��ſ�ͷ6λ�����Ϲ淶");
			return false;
		}
        var lastNum=bankno.substr(bankno.length-1,1);//ȡ�����һλ����luhm���бȽϣ�
    
        var first15Num=bankno.substr(0,bankno.length-1);//ǰ15��18λ
        var newArr=new Array();
        for(var i=first15Num.length-1;i>-1;i--){    //ǰ15��18λ����������
            newArr.push(first15Num.substr(i,1));
        }
        var arrJiShu=new Array();  //����λ*2�Ļ� <9
        var arrJiShu2=new Array(); //����λ*2�Ļ� >9
        
        var arrOuShu=new Array();  //ż��λ����
        for(var j=0;j<newArr.length;j++){
            if((j+1)%2==1){//����λ
                if(parseInt(newArr[j])*2<9)
                arrJiShu.push(parseInt(newArr[j])*2);
                else
                arrJiShu2.push(parseInt(newArr[j])*2);
            }
            else //ż��λ
            arrOuShu.push(newArr[j]);
        }
        
        var jishu_child1=new Array();//����λ*2 >9 �ķָ�֮��������λ��
        var jishu_child2=new Array();//����λ*2 >9 �ķָ�֮�������ʮλ��
        for(var h=0;h<arrJiShu2.length;h++){
            jishu_child1.push(parseInt(arrJiShu2[h])%10);
            jishu_child2.push(parseInt(arrJiShu2[h])/10);
        }        
        
        var sumJiShu=0; //����λ*2 < 9 ������֮��
        var sumOuShu=0; //ż��λ����֮��
        var sumJiShuChild1=0; //����λ*2 >9 �ķָ�֮��������λ��֮��
        var sumJiShuChild2=0; //����λ*2 >9 �ķָ�֮�������ʮλ��֮��
        var sumTotal=0;
        for(var m=0;m<arrJiShu.length;m++){
            sumJiShu=sumJiShu+parseInt(arrJiShu[m]);
        }
        
        for(var n=0;n<arrOuShu.length;n++){
            sumOuShu=sumOuShu+parseInt(arrOuShu[n]);
        }
        
        for(var p=0;p<jishu_child1.length;p++){
            sumJiShuChild1=sumJiShuChild1+parseInt(jishu_child1[p]);
            sumJiShuChild2=sumJiShuChild2+parseInt(jishu_child2[p]);
        }      
        //�����ܺ�
        sumTotal=parseInt(sumJiShu)+parseInt(sumOuShu)+parseInt(sumJiShuChild1)+parseInt(sumJiShuChild2);
        
        //����Luhmֵ
        var k= parseInt(sumTotal)%10==0?10:parseInt(sumTotal)%10;        
        var luhm= 10-k;
        
        if(lastNum==luhm){
	        return true;
        }
        else{
	        alert("���п��ű������LuhmУ��");
	        return false;
        }        
    }