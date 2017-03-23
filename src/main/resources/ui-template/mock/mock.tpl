const qs = require('qs')
const Mock = require('mockjs')
import mockStorge from '../src/utils/mockStorge'

let dataKey = mockStorge('<#$entity.entityClassName#>sList', Mock.mock({
  'data|10': [
    {
      'id|+1': 1,
      <#foreach from=$entity.fields item=field#>
          <#$field.name#>: '@<#$field.name#>',
       <#/foreach#>
    },
  ],
  page: {
    total: 10,
    current: 1,
  },
}))

let <#$entity.path#>ListData = global[dataKey]

module.exports = {

  'GET /api/<#$entity.path#>' (req, res) {
    const page = qs.parse(req.query)
    const pageSize = page.pageSize || 10
    const currentPage = page.page || 1

    let data
    let newPage

    let newData = <#$entity.path#>ListData.data.concat()

    if (page.field) {
      const d = newData.filter((item) => {
        return item[page.field].indexOf(decodeURI(page.keyword)) > -1
      })

      data = d.slice((currentPage - 1) * pageSize, currentPage * pageSize)

      newPage = {
        current: currentPage * 1,
        total: d.length,
      }
    } else {
      data = <#$entity.path#>ListData.data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
      <#$entity.path#>ListData.page.current = currentPage * 1
      newPage = <#$entity.path#>ListData.page
    }
    res.json({ success: true, data, page: { ...newPage, pageSize: Number(pageSize) } })
  },

  'POST /api/<#$entity.path#>' (req, res) {
    const newData = req.body

    newData.id = <#$entity.path#>ListData.data.length + 1

    <#$entity.path#>ListData.data.unshift(newData)

    <#$entity.path#>ListData.page.total = <#$entity.path#>ListData.data.length
    <#$entity.path#>ListData.page.current = 1

    global[dataKey] = <#$entity.path#>ListData

    res.json({ success: true, data: <#$entity.path#>ListData.data, page: <#$entity.path#>ListData.page })
  },

  'DELETE /api/<#$entity.path#>' (req, res) {
    const deleteItem = req.body

    <#$entity.path#>ListData.data = <#$entity.path#>ListData.data.filter((item) => {
      if (item.id === deleteItem.id) {
        return false
      }
      return true
    })

    <#$entity.path#>ListData.page.total = <#$entity.path#>ListData.data.length

    global[dataKey] = <#$entity.path#>ListData

    res.json({ success: true, data: <#$entity.path#>ListData.data, page: <#$entity.path#>ListData.page })
  },

  'PUT /api/<#$entity.path#>' (req, res) {
    const editItem = req.body

    editItem.createTime = Mock.mock('@now')
    editItem.avatar = Mock.Random.image('100x100', Mock.Random.color(), '#757575', 'png', editItem.nickName.substr(0, 1))

    <#$entity.path#>ListData.data = <#$entity.path#>ListData.data.map((item) => {
      if (item.id === editItem.id) {
        return editItem
      }
      return item
    })

    global[dataKey] = <#$entity.path#>ListData
    res.json({ success: true, data: <#$entity.path#>ListData.data, page: <#$entity.path#>ListData.page })
  },

}
