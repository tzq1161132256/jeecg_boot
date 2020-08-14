<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="项目名称">
          <a-input placeholder="请输入项目名称" v-decorator="['projectname', validatorRules.projectname ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="订单号">
          <a-input placeholder="请输入订单号" v-decorator="['orderno', validatorRules.orderno ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="保单编号">
          <a-input placeholder="请输入保单编号" v-decorator="['policyno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请人姓名(投保人) 敏感加密">
          <a-input placeholder="请输入申请人姓名(投保人) 敏感加密" v-decorator="['policyholdername', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请人手机号码(投保人) ">
          <a-input placeholder="请输入申请人手机号码(投保人) " v-decorator="['policyholdermobile', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请人身份证号码(投保人)">
          <a-input placeholder="请输入申请人身份证号码(投保人)" v-decorator="['policyholderidcardno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="份数">
          <a-input placeholder="请输入份数" v-decorator="['copies', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="区域">
          <a-input placeholder="请输入区域" v-decorator="['areaname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="省份">
          <a-input placeholder="请输入省份" v-decorator="['provincename', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="城市">
          <a-input placeholder="请输入城市" v-decorator="['cityname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="区县">
          <a-input placeholder="请输入区县" v-decorator="['countyname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="状态">
          <a-input placeholder="请输入状态" v-decorator="['status', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'applicationdate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="审核日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'auditdate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="被保险人姓名">
          <a-input placeholder="请输入被保险人姓名" v-decorator="['insuredname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="被保险人手机号码">
          <a-input placeholder="请输入被保险人手机号码" v-decorator="['insuredmobile', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="被保险人身份证号码">
          <a-input placeholder="请输入被保险人身份证号码" v-decorator="['insuredidcardno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="受益人姓名">
          <a-input placeholder="请输入受益人姓名" v-decorator="['beneficiaryname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="受益人手机号码">
          <a-input placeholder="请输入受益人手机号码" v-decorator="['beneficiarymobile', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="受益人身份证号码">
          <a-input placeholder="请输入受益人身份证号码" v-decorator="['beneficiaryidcardno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否金域扫码">
          <a-input placeholder="请输入是否金域扫码" v-decorator="['scanflag', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注1">
          <a-input placeholder="请输入备注1" v-decorator="['remark1', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注2">
          <a-input placeholder="请输入备注2" v-decorator="['remark2', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="最后更新时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'datelastupdate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="创建日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'createdate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="审核成功时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'auditfaildate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="审核失败时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'auditsuccessdate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="保单生成日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'policydate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="购药日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'purchasedate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="药房名称">
          <a-input placeholder="请输入药房名称" v-decorator="['drugstorename', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="医院名称">
          <a-input placeholder="请输入医院名称" v-decorator="['hospitalname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="删除标识0-正常,1-已删除">
          <a-input placeholder="请输入删除标识0-正常,1-已删除" v-decorator="['delFlag', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "InsuranceModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        projectname:{rules: [{ required: true, message: '请输入项目名称!' }]},
        orderno:{rules: [{ required: true, message: '请输入订单号!' }]},
        },
        url: {
          add: "/trade/insurance/add",
          edit: "/trade/insurance/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'projectname','orderno','policyno','policyholdername','policyholdermobile','policyholderidcardno','copies','areaname','provincename','cityname','countyname','status','insuredname','insuredmobile','insuredidcardno','beneficiaryname','beneficiarymobile','beneficiaryidcardno','scanflag','remark1','remark2','drugstorename','hospitalname','delFlag'))
		  //时间格式化
          this.form.setFieldsValue({applicationdate:this.model.applicationdate?moment(this.model.applicationdate):null})
          this.form.setFieldsValue({auditdate:this.model.auditdate?moment(this.model.auditdate):null})
          this.form.setFieldsValue({datelastupdate:this.model.datelastupdate?moment(this.model.datelastupdate):null})
          this.form.setFieldsValue({createdate:this.model.createdate?moment(this.model.createdate):null})
          this.form.setFieldsValue({auditfaildate:this.model.auditfaildate?moment(this.model.auditfaildate):null})
          this.form.setFieldsValue({auditsuccessdate:this.model.auditsuccessdate?moment(this.model.auditsuccessdate):null})
          this.form.setFieldsValue({policydate:this.model.policydate?moment(this.model.policydate):null})
          this.form.setFieldsValue({purchasedate:this.model.purchasedate?moment(this.model.purchasedate):null})
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.applicationdate = formData.applicationdate?formData.applicationdate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.auditdate = formData.auditdate?formData.auditdate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.datelastupdate = formData.datelastupdate?formData.datelastupdate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.createdate = formData.createdate?formData.createdate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.auditfaildate = formData.auditfaildate?formData.auditfaildate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.auditsuccessdate = formData.auditsuccessdate?formData.auditsuccessdate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.policydate = formData.policydate?formData.policydate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.purchasedate = formData.purchasedate?formData.purchasedate.format('YYYY-MM-DD HH:mm:ss'):null;
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>