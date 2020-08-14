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
              label="业务系统中的订单ID">
              <a-input placeholder="请输入业务系统中的订单ID" v-decorator="['orderid', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单日期">
              <a-date-picker
                placeholder="请输入订单日期"
                style="width:100%"
                :showTime="true"
                format="YYYY-MM-DD HH:mm:ss"
                v-decorator="[ 'orderdate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单类型，送药到家/送药到家">
              <a-input placeholder="请输入订单类型，送药到家/送药到家" v-decorator="['ordertype', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单编码">
              <a-input placeholder="请输入订单编码" v-decorator="['ordercode', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="医院名称">
              <a-input placeholder="请输入医院名称" v-decorator="['hospitalname', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="医生名称">
              <a-input placeholder="请输入医生名称" v-decorator="['doctorname', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="是否项目医生">
              <a-input placeholder="请输入是否项目医生" v-decorator="['doctorflag', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药店省份">
              <a-input placeholder="请输入药店省份" v-decorator="['drugstoreprovincename', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药店城市">
              <a-input placeholder="请输入药店城市" v-decorator="['drugstorecityname', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药店名称">
              <a-input placeholder="请输入药店名称" v-decorator="['drugstorename', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="患者名称">
              <a-input placeholder="请输入患者名称" v-decorator="['patientname', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="患者电话">
              <a-input placeholder="请输入患者电话" v-decorator="['patientmobile', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="购买人名称">
              <a-input placeholder="请输入购买人名称" v-decorator="['buyername', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="购买人电话">
              <a-input placeholder="请输入购买人电话" v-decorator="['buyermobile', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="收货人名称">
              <a-input placeholder="请输入收货人名称" v-decorator="['consigneename', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="收货人电话">
              <a-input placeholder="请输入收货人电话" v-decorator="['consigneemobile', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="收货地址">
              <a-input placeholder="请输入收货地址" v-decorator="['consigneeaddress', {}]"/>
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
              label="记录修改时间">
              <a-date-picker
                placeholder="请输入记录修改时间"
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
              label="订单状态">
              <a-input placeholder="请输入订单状态" v-decorator="['status', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单配送时间">
              <a-input placeholder="请输入订单配送时间" v-decorator="['deliverytime', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="适应症">
              <a-input placeholder="请输入适应症" v-decorator="['indication', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="适应症分类">
              <a-input placeholder="请输入适应症分类" v-decorator="['indicationtype', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="最后更新人">
              <a-input placeholder="请输入最后更新人" v-decorator="['updateuser', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="项目名称">
              <a-input placeholder="请输入项目名称" v-decorator="['projectname', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="患者身份证号码">
              <a-input placeholder="请输入患者身份证号码" v-decorator="['patientidcard', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="购买人身份证号码">
              <a-input placeholder="请输入购买人身份证号码" v-decorator="['buyeridcard', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药房结算金额">
              <a-input-number placeholder="请输入药房结算金额" style="width:100%" v-decorator="[ 'amount', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="折扣">
              <a-input-number placeholder="请输入折扣" style="width:100%" v-decorator="[ 'discount', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="折后总金额">
              <a-input-number placeholder="请输入折后总金额" style="width:100%" v-decorator="[ 'afterdiscountamount', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="扣款通道">
              <a-input placeholder="请输入扣款通道" v-decorator="['paychannel', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="手续费">
              <a-input-number placeholder="请输入手续费" style="width:100%" v-decorator="[ 'servicecharge', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="补贴金额">
              <a-input-number placeholder="请输入补贴金额" style="width:100%" v-decorator="[ 'subsidyamount', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="到账金额">
              <a-input-number placeholder="请输入到账金额" style="width:100%" v-decorator="[ 'amountaccount', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="备注">
              <a-input placeholder="请输入备注" v-decorator="['remark', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="结算情况">
              <a-input placeholder="请输入结算情况" v-decorator="['settlementstatus', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="打款日">
              <a-date-picker
                placeholder="请输入打款日"
                style="width:100%"
                :showTime="true"
                format="YYYY-MM-DD HH:mm:ss"
                v-decorator="[ 'settlementdate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="是否清洗主数据">
              <a-input placeholder="请输入是否清洗主数据" v-decorator="['clearflag', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="患者id">
              <a-input placeholder="请输入患者id" v-decorator="['patientid', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药房id">
              <a-input placeholder="请输入药房id" v-decorator="['drugstoreid', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="是否已有物流信息">
              <a-input placeholder="请输入是否已有物流信息" v-decorator="['logisticsflag', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="预计下次购药日期">
              <a-date-picker
                placeholder="请输入预计下次购药日期"
                style="width:100%"
                v-decorator="[ 'nextpurchasetime', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单来源（默沙东精准肝愈）WECHAT/PAD">
              <a-input placeholder="请输入订单来源（默沙东精准肝愈）WECHAT/PAD" v-decorator="['ordersource', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="是否会员订单(BPM项目)">
              <a-input placeholder="请输入是否会员订单(BPM项目)" v-decorator="['memberorderflag', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用户类型(关爱卡/健康卡/康付卡) (BPM项目)">
              <a-input placeholder="请输入用户类型(关爱卡/健康卡/康付卡) (BPM项目)" v-decorator="['usertype', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药房渠道（康德乐/众协）(BPM项目)">
              <a-input placeholder="请输入药房渠道（康德乐/众协）(BPM项目)" v-decorator="['drugstorechannel', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="药店返点收入(BPM项目)">
              <a-input-number placeholder="请输入药店返点收入(BPM项目)" style="width:100%" v-decorator="[ 'rebate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="净补贴(BPM项目)">
              <a-input-number placeholder="请输入净补贴(BPM项目)" style="width:100%" v-decorator="[ 'netsubsidy', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="业务区域名称">
              <a-input placeholder="请输入业务区域名称" v-decorator="['areaname', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="标识线下导入的数据">
              <a-input placeholder="请输入标识线下导入的数据" v-decorator="['remark2', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用券数量">
              <a-input-number placeholder="请输入用券数量" style="width:100%" v-decorator="[ 'ticketcount', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="不通过原因">
              <a-input placeholder="请输入不通过原因" v-decorator="['reason', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="期望配送日期">
              <a-date-picker
                placeholder="请输入期望配送日期"
                style="width:100%"
                :showTime="true"
                format="YYYY-MM-DD HH:mm:ss"
                v-decorator="[ 'expecteddeliverydate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="医院省份">
              <a-input placeholder="请输入医院省份" v-decorator="['hospitalprovincename', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="医院城市">
              <a-input placeholder="请输入医院城市" v-decorator="['hospitalcityname', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="处方量">
              <a-input placeholder="请输入处方量" v-decorator="['prescriptionvolume', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="是否退款">
              <a-input placeholder="请输入是否退款" v-decorator="['refundflag', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="退款原因">
              <a-input placeholder="请输入退款原因" v-decorator="['refundreason', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="项目来源名称">
              <a-input placeholder="请输入项目来源名称" v-decorator="['remark3', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="订单明细" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="orderdetailTable.loading"
            :columns="orderdetailTable.columns"
            :dataSource="orderdetailTable.dataSource"
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
    name: 'OrderModal',
    mixins: [JEditableTableMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['orderdetail', ],
        activeKey: 'orderdetail',
        // 订单明细
        orderdetailTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '业务系统中订单明细ID',
              key: 'orderdetailid',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: 'BI系统中的订单ID',
              key: 'orderid',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '药品名称',
              key: 'medname',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '销量',
              key: 'qty',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '适应症',
              key: 'indication',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '适应症分类',
              key: 'indicationtype',
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
              title: '最后更新时间',
              key: 'datelastupdate',
              type: FormTypes.datetime,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '订单编码',
              key: 'ordercode',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '规格',
              key: 'spec',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '单位',
              key: 'unit',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '厂家',
              key: 'manufactor',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '药品编码',
              key: 'medcode',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '单价',
              key: 'unitprice',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '补贴金额 （BMS项目）',
              key: 'subsidyamount',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/trade/order/add",
          edit: "/trade/order/edit",
          orderdetail: {
            list: '/trade/order/queryOrderdetailByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'orderid', 'orderdate', 'ordertype', 'ordercode', 'hospitalname', 'doctorname', 'doctorflag', 'drugstoreprovincename', 'drugstorecityname', 'drugstorename', 'patientname', 'patientmobile', 'buyername', 'buyermobile', 'consigneename', 'consigneemobile', 'consigneeaddress', 'createdate', 'datelastupdate', 'status', 'deliverytime', 'indication', 'indicationtype', 'updateuser', 'projectname', 'patientidcard', 'buyeridcard', 'amount', 'discount', 'afterdiscountamount', 'paychannel', 'servicecharge', 'subsidyamount', 'amountaccount', 'remark', 'settlementstatus', 'settlementdate', 'clearflag', 'patientid', 'drugstoreid', 'logisticsflag', 'nextpurchasetime', 'ordersource', 'memberorderflag', 'usertype', 'drugstorechannel', 'rebate', 'netsubsidy', 'areaname', 'remark2', 'ticketcount', 'reason', 'expecteddeliverydate', 'hospitalprovincename', 'hospitalcityname', 'prescriptionvolume', 'refundflag', 'refundreason', 'remark3', ))
          // 时间格式化
          this.form.setFieldsValue({ orderdate: this.model.orderdate ? moment(this.model.orderdate) : null })
          this.form.setFieldsValue({ createdate: this.model.createdate ? moment(this.model.createdate) : null })
          this.form.setFieldsValue({ datelastupdate: this.model.datelastupdate ? moment(this.model.datelastupdate) : null })
          this.form.setFieldsValue({ settlementdate: this.model.settlementdate ? moment(this.model.settlementdate) : null })
          this.form.setFieldsValue({ nextpurchasetime: this.model.nextpurchasetime ? moment(this.model.nextpurchasetime) : null })
          this.form.setFieldsValue({ expecteddeliverydate: this.model.expecteddeliverydate ? moment(this.model.expecteddeliverydate) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.orderdetail.list, params, this.orderdetailTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        main.orderdate = main.orderdate ? main.orderdate.format('YYYY-MM-DD HH:mm:ss') : null;
        main.createdate = main.createdate ? main.createdate.format('YYYY-MM-DD HH:mm:ss') : null;
        main.datelastupdate = main.datelastupdate ? main.datelastupdate.format('YYYY-MM-DD HH:mm:ss') : null;
        main.settlementdate = main.settlementdate ? main.settlementdate.format('YYYY-MM-DD HH:mm:ss') : null;
        main.nextpurchasetime = main.nextpurchasetime ? main.nextpurchasetime.format() : null;
        main.expecteddeliverydate = main.expecteddeliverydate ? main.expecteddeliverydate.format('YYYY-MM-DD HH:mm:ss') : null;
        return {
          ...main, // 展开
          orderdetailList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>