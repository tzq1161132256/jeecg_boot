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
          label="订单号">
          <a-input placeholder="请输入订单号" v-decorator="['orderno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="手机">
          <a-input placeholder="请输入手机" v-decorator="['mobile', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="姓名">
          <a-input placeholder="请输入姓名" v-decorator="['name', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请数量">
          <a-input-number v-decorator="[ 'applyqty', {}]" />
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
          label="报销申请日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'applydate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="个人凭证号">
          <a-input placeholder="请输入个人凭证号" v-decorator="['idcard', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="赔案号">
          <a-input placeholder="请输入赔案号" v-decorator="['claimno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="业务系统中更新时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'modifydate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="项目名称">
          <a-input placeholder="请输入项目名称" v-decorator="['projectname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="理赔金额">
          <a-input-number v-decorator="[ 'amount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="处方医院">
          <a-input placeholder="请输入处方医院" v-decorator="['attr1', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="处方医院省份">
          <a-input placeholder="请输入处方医院省份" v-decorator="['attr2', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="处方医院城市">
          <a-input placeholder="请输入处方医院城市" v-decorator="['attr3', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="理赔日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'attr4', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发票日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'attr5', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="attr6">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'attr6', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="createdate">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'createdate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="datelastupdate">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'datelastupdate', {}]" />
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
          label="药房省份">
          <a-input placeholder="请输入药房省份" v-decorator="['drugstoreprovincename', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="药房城市">
          <a-input placeholder="请输入药房城市" v-decorator="['drugstorecityname', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="实际购药数量">
          <a-input placeholder="请输入实际购药数量" v-decorator="['purchaseqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="处方量">
          <a-input placeholder="请输入处方量" v-decorator="['prescriptionvolume', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身份证号码">
          <a-input placeholder="请输入身份证号码" v-decorator="['idnumber', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="性别">
          <a-input placeholder="请输入性别" v-decorator="['sex', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户编码">
          <a-input placeholder="请输入用户编码" v-decorator="['usercode', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="保险申请日期">
          <a-date-picker v-decorator="[ 'applydateInsurance', {}]" />
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
    name: "InsuranceClaimsModal",
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
        },
        url: {
          add: "/trade/insuranceClaims/add",
          edit: "/trade/insuranceClaims/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderno','mobile','name','applyqty','status','idcard','claimno','projectname','amount','attr1','attr2','attr3','drugstorename','drugstoreprovincename','drugstorecityname','purchaseqty','prescriptionvolume','idnumber','sex','usercode','delFlag'))
		  //时间格式化
          this.form.setFieldsValue({applydate:this.model.applydate?moment(this.model.applydate):null})
          this.form.setFieldsValue({modifydate:this.model.modifydate?moment(this.model.modifydate):null})
          this.form.setFieldsValue({attr4:this.model.attr4?moment(this.model.attr4):null})
          this.form.setFieldsValue({attr5:this.model.attr5?moment(this.model.attr5):null})
          this.form.setFieldsValue({attr6:this.model.attr6?moment(this.model.attr6):null})
          this.form.setFieldsValue({createdate:this.model.createdate?moment(this.model.createdate):null})
          this.form.setFieldsValue({datelastupdate:this.model.datelastupdate?moment(this.model.datelastupdate):null})
          this.form.setFieldsValue({applydateInsurance:this.model.applydateInsurance?moment(this.model.applydateInsurance):null})
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
            formData.applydate = formData.applydate?formData.applydate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.modifydate = formData.modifydate?formData.modifydate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.attr4 = formData.attr4?formData.attr4.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.attr5 = formData.attr5?formData.attr5.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.attr6 = formData.attr6?formData.attr6.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.createdate = formData.createdate?formData.createdate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.datelastupdate = formData.datelastupdate?formData.datelastupdate.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.applydateInsurance = formData.applydateInsurance?formData.applydateInsurance.format():null;
            
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