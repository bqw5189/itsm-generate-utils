module.exports = [
  {
      key: 'businesss',
      name: '企业信息',
      icon: 'setting',
  },
  {
      key: 'deparments',
      name: '部门信息',
      icon: 'setting',
  },
  {
      key: 'roles',
      name: '角色管理',
      icon: 'setting',
  },
  {
      key: 'users',
      name: '用户管理',
      icon: 'user',
  },
  {
      key: 'functions',
      name: '菜单信息',
      icon: 'setting',
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
