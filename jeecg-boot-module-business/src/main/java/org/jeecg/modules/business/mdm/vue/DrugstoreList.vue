<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="编码">
              <a-input placeholder="请输入编码" v-model="queryParam.code"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="名称">
              <a-input placeholder="请输入名称" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="省份">
              <a-input placeholder="请输入省份" v-model="queryParam.provincename"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="城市">
              <a-input placeholder="请输入城市" v-model="queryParam.cityname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="区县">
              <a-input placeholder="请输入区县" v-model="queryParam.countyname"></a-input>
            </a-form-item>
          </a-col>
        </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('药店主数据')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <drugstore-modal ref="modalForm" @ok="modalFormOk"></drugstore-modal>
  </a-card>
</template>

<script>
  import DrugstoreModal from './modules/DrugstoreModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "DrugstoreList",
    mixins:[JeecgListMixin],
    components: {
      DrugstoreModal
    },
    data () {
      return {
        description: '药店主数据管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '编码',
            align:"center",
            dataIndex: 'code'
           },
		   {
            title: '名称',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '省份',
            align:"center",
            dataIndex: 'provincename'
           },
		   {
            title: '城市',
            align:"center",
            dataIndex: 'cityname'
           },
		   {
            title: '区县',
            align:"center",
            dataIndex: 'countyname'
           },
		   {
            title: '业务区域',
            align:"center",
            dataIndex: 'areaname'
           },
		   {
            title: '最后更新人',
            align:"center",
            dataIndex: 'updateuser'
           },
		   {
            title: '记录生成时间',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: '最后更新时间',
            align:"center",
            dataIndex: 'datelastupdate'
           },
		   {
            title: '地址',
            align:"center",
            dataIndex: 'address'
           },
		   {
            title: '状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: '联系人',
            align:"center",
            dataIndex: 'contact'
           },
		   {
            title: '联系人电话',
            align:"center",
            dataIndex: 'contactmobile'
           },
		   {
            title: 'telephone',
            align:"center",
            dataIndex: 'telephone'
           },
		   {
            title: 'channelname',
            align:"center",
            dataIndex: 'channelname'
           },
		   {
            title: 'longitude',
            align:"center",
            dataIndex: 'longitude'
           },
		   {
            title: 'latitude',
            align:"center",
            dataIndex: 'latitude'
           },
		   {
            title: 'category',
            align:"center",
            dataIndex: 'category'
           },
		   {
            title: 'drugstoretype',
            align:"center",
            dataIndex: 'drugstoretype'
           },
		   {
            title: 'attr1',
            align:"center",
            dataIndex: 'attr1'
           },
		   {
            title: 'creditcode',
            align:"center",
            dataIndex: 'creditcode'
           },
		   {
            title: 'attr2',
            align:"center",
            dataIndex: 'attr2'
           },
		   {
            title: 'attr3',
            align:"center",
            dataIndex: 'attr3'
           },
		   {
            title: '删除标识0-正常,1-已删除',
            align:"center",
            dataIndex: 'delFlag'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/mdm/drugstore/list",
          delete: "/mdm/drugstore/delete",
          deleteBatch: "/mdm/drugstore/deleteBatch",
          exportXlsUrl: "mdm/drugstore/exportXls",
          importExcelUrl: "mdm/drugstore/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>