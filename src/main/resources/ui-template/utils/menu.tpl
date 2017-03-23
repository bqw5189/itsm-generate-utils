module.exports = [
  {
    key: 'dashboard',
    name: '仪表盘',
    icon: 'laptop',
  },
  {
    key: 'users',
    name: '用户管理',
    icon: 'user',
  },
  {
    key: 'ui',
    name: 'UI组件',
    icon: 'camera-o',
    clickable: false,
    child: [
      {
        key: 'ico',
        name: 'Ico 图标',
      },
      {
        key: 'search',
        name: 'Search 搜索',
      },
      {
        key: 'dropOption',
        name: 'DropOption 下拉操作',
      },
      {
        key: 'layer',
        name: 'Layer 弹层',
      },
      {
        key: 'DataTable',
        name: 'DataTable 数据表格',
      },
    ],
  },
  {
    key: 'auto',
    name: '自动生成模块',
    icon: 'setting',
    child: [
    <#foreach from=$entitys item=m#>
      {
        key: '<#$m.path#>',
        name: '<#$m.entityName#>',
      },
    <#/foreach#>
    ],
  },
]
