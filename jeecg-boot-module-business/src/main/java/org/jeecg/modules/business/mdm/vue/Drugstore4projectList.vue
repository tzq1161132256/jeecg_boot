<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="更新时间">
              <a-input placeholder="请输入更新时间" v-model="queryParam.createdate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="最后更新时间">
              <a-input placeholder="请输入最后更新时间" v-model="queryParam.datelastupdate"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="项目名称">
              <a-input placeholder="请输入项目名称" v-model="queryParam.projectname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="药店名称">
              <a-input placeholder="请输入药店名称" v-model="queryParam.drugstorename"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="渠道名称">
              <a-input placeholder="请输入渠道名称" v-model="queryParam.channelname"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('项目对应的药店')">导出</a-button>
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
    <drugstore4project-modal ref="modalForm" @ok="modalFormOk"></drugstore4project-modal>
  </a-card>
</template>

<script>
  import Drugstore4projectModal from './modules/Drugstore4projectModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "Drugstore4projectList",
    mixins:[JeecgListMixin],
    components: {
      Drugstore4projectModal
    },
    data () {
      return {
        description: '项目对应的药店管理页面',
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
            title: '更新时间',
            align:"center",
            dataIndex: 'createdate'
           },
		   {
            title: '最后更新时间',
            align:"center",
            dataIndex: 'datelastupdate'
           },
		   {
            title: '项目名称',
            align:"center",
            dataIndex: 'projectname'
           },
		   {
            title: '药店名称',
            align:"center",
            dataIndex: 'drugstorename'
           },
		   {
            title: '渠道名称',
            align:"center",
            dataIndex: 'channelname'
           },
		   {
            title: '状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: '联系电话',
            align:"center",
            dataIndex: 'telphone'
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
            title: '地址',
            align:"center",
            dataIndex: 'address'
           },
		   {
            title: '经度',
            align:"center",
            dataIndex: 'longitude'
           },
		   {
            title: '纬度',
            align:"center",
            dataIndex: 'latitude'
           },
		   {
            title: '药店id',
            align:"center",
            dataIndex: 'drugstoreid'
           },
		   {
            title: '是否删除',
            align:"center",
            dataIndex: 'deleted'
           },
		   {
            title: '是否营销药房',
            align:"center",
            dataIndex: 'marketingflag'
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
          list: "/mdm/drugstore4project/list",
          delete: "/mdm/drugstore4project/delete",
          deleteBatch: "/mdm/drugstore4project/deleteBatch",
          exportXlsUrl: "mdm/drugstore4project/exportXls",
          importExcelUrl: "mdm/drugstore4project/importExcel",
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