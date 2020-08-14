<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药房id">
              <a-input placeholder="请输入药房id" v-decorator="['pharmacyid', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药房code">
              <a-input placeholder="请输入药房code" v-decorator="['pharmacycode', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药房名称">
              <a-input placeholder="请输入药房名称" v-decorator="['pharmacyname', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药企对应的药房名称">
              <a-input placeholder="请输入药企对应的药房名称" v-decorator="['pharmacyname4partner', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议状态">
              <a-input placeholder="请输入pos协议状态" v-decorator="['posagreementstatus', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos状态">
              <a-input placeholder="请输入pos状态" v-decorator="['posstatus', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad状态">
              <a-input placeholder="请输入pad状态" v-decorator="['padstatus', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="记录生成时间">
              <a-date-picker
                placeholder="请输入记录生成时间"
                style="width:100%"
                :showTime="true"
                format="YYYY-MM-DD HH:mm:ss"
                v-decorator="[ 'createdate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="记录更新时间">
              <a-date-picker
                placeholder="请输入记录更新时间"
                style="width:100%"
                :showTime="true"
                format="YYYY-MM-DD HH:mm:ss"
                v-decorator="[ 'datelastupdate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="删除标志 Y 删除 N未删除">
              <a-input placeholder="请输入删除标志 Y 删除 N未删除" v-decorator="['deleteflag', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议快递单号">
              <a-input placeholder="请输入pos协议快递单号" v-decorator="['posagreementexpressno', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议邮寄日期">
              <a-date-picker
                placeholder="请输入pos协议邮寄日期"
                style="width:100%"
                v-decorator="[ 'posagreementpostdate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议收货人">
              <a-input placeholder="请输入pos协议收货人" v-decorator="['posagreementconsignee', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议收货人电话">
              <a-input placeholder="请输入pos协议收货人电话" v-decorator="['posagreementtelphone', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议项目名称">
              <a-input placeholder="请输入pos协议项目名称" v-decorator="['posagreementproject', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议经办人">
              <a-input placeholder="请输入pos协议经办人" v-decorator="['posagreementoperator', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos协议备注">
              <a-input placeholder="请输入pos协议备注" v-decorator="['posagreementremark', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos快递单号">
              <a-input placeholder="请输入pos快递单号" v-decorator="['posexpressno', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos邮寄日期">
              <a-date-picker
                placeholder="请输入pos邮寄日期"
                style="width:100%"
                v-decorator="[ 'pospostdate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos收货人">
              <a-input placeholder="请输入pos收货人" v-decorator="['posconsignee', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos收货人电话">
              <a-input placeholder="请输入pos收货人电话" v-decorator="['postelphone', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos项目名称">
              <a-input placeholder="请输入pos项目名称" v-decorator="['posproject', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos经办人">
              <a-input placeholder="请输入pos经办人" v-decorator="['posoperator', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pos备注">
              <a-input placeholder="请输入pos备注" v-decorator="['posremark', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad协议快递单号">
              <a-input placeholder="请输入pad协议快递单号" v-decorator="['padexpressno', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad协议邮寄日期">
              <a-date-picker
                placeholder="请输入pad协议邮寄日期"
                style="width:100%"
                v-decorator="[ 'padpostdate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad协议收货人">
              <a-input placeholder="请输入pad协议收货人" v-decorator="['padconsignee', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad协议收货人电话">
              <a-input placeholder="请输入pad协议收货人电话" v-decorator="['padtelphone', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad协议项目名称">
              <a-input placeholder="请输入pad协议项目名称" v-decorator="['padproject', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad协议经办人">
              <a-input placeholder="请输入pad协议经办人" v-decorator="['padoperator', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="pad协议备注">
              <a-input placeholder="请输入pad协议备注" v-decorator="['padremark', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="删除标识0-正常,1-已删除">
              <a-input placeholder="请输入删除标识0-正常,1-已删除" v-decorator="['delFlag', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="药房pos机pad协议管理明细" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="pharmacyequipment4projectTable.loading"
            :columns="pharmacyequipment4projectTable.columns"
            :dataSource="pharmacyequipment4projectTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'

  export default {
    name: 'PharmacyequipmentModal',
    mixins: [JEditableTableMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['pharmacyequipment4project', ],
        activeKey: 'pharmacyequipment4project',
        // 药房pos机pad协议管理明细
        pharmacyequipment4projectTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '药房id',
              key: 'pharmacyequipmentid',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '项目名称',
              key: 'projectname',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '协议书状态',
              key: 'agreementstatus',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '记录生成时间',
              key: 'createdate',
              type: FormTypes.datetime,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '记录最后更新时间',
              key: 'datelastupdate',
              type: FormTypes.datetime,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '删除标志 Y 删除 N未删除',
              key: 'deleteflag',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '协议快递单号',
              key: 'expressno',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '协议邮寄日期',
              key: 'postdate',
              type: FormTypes.date,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '协议收货人',
              key: 'consignee',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '协议收货人电话',
              key: 'telphone',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '协议经办人',
              key: 'operator',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '协议备注',
              key: 'remark',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '状态变更时间',
              key: 'statusmodifydate',
              type: FormTypes.datetime,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '删除标识0-正常,1-已删除',
              key: 'delFlag',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/mdm/pharmacyequipment/add",
          edit: "/mdm/pharmacyequipment/edit",
          pharmacyequipment4project: {
            list: '/mdm/pharmacyequipment/queryPharmacyequipment4projectByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'pharmacyid', 'pharmacycode', 'pharmacyname', 'pharmacyname4partner', 'posagreementstatus', 'posstatus', 'padstatus', 'createdate', 'datelastupdate', 'deleteflag', 'posagreementexpressno', 'posagreementpostdate', 'posagreementconsignee', 'posagreementtelphone', 'posagreementproject', 'posagreementoperator', 'posagreementremark', 'posexpressno', 'pospostdate', 'posconsignee', 'postelphone', 'posproject', 'posoperator', 'posremark', 'padexpressno', 'padpostdate', 'padconsignee', 'padtelphone', 'padproject', 'padoperator', 'padremark', 'delFlag', ))
          // 时间格式化
          this.form.setFieldsValue({ createdate: this.model.createdate ? moment(this.model.createdate) : null })
          this.form.setFieldsValue({ datelastupdate: this.model.datelastupdate ? moment(this.model.datelastupdate) : null })
          this.form.setFieldsValue({ posagreementpostdate: this.model.posagreementpostdate ? moment(this.model.posagreementpostdate) : null })
          this.form.setFieldsValue({ pospostdate: this.model.pospostdate ? moment(this.model.pospostdate) : null })
          this.form.setFieldsValue({ padpostdate: this.model.padpostdate ? moment(this.model.padpostdate) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.pharmacyequipment4project.list, params, this.pharmacyequipment4projectTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        main.createdate = main.createdate ? main.createdate.format('YYYY-MM-DD HH:mm:ss') : null;
        main.datelastupdate = main.datelastupdate ? main.datelastupdate.format('YYYY-MM-DD HH:mm:ss') : null;
        main.posagreementpostdate = main.posagreementpostdate ? main.posagreementpostdate.format() : null;
        main.pospostdate = main.pospostdate ? main.pospostdate.format() : null;
        main.padpostdate = main.padpostdate ? main.padpostdate.format() : null;
        return {
          ...main, // 展开
          pharmacyequipment4projectList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>