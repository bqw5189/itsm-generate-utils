module.exports = [
  {
    key: 'users',
    name: '用户管理',
    icon: 'user',
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
