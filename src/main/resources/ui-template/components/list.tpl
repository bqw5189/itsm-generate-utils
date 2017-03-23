import React, { PropTypes } from 'react'
import { Table, Modal } from 'antd'
import styles from './list.less'
import classnames from 'classnames'
import TableBodyWrapper from '../common/TableBodyWrapper'
import { DropOption } from '../ui/index'

const confirm = Modal.confirm

function list ({ loading, dataSource, pagination, onPageChange, onDeleteItem, onEditItem, isMotion, location }) {
  const handleMenuClick = (record, e) => {
    if (e.key === '1') {
      onEditItem(record)
    } else if (e.key === '2') {
      confirm({
        title: '您确定要删除这条记录吗?',
        onOk () {
          onDeleteItem(record.id)
        },
      })
    }
  }

  const columns = [
  <#foreach from=$entity.fields item=field#>
    {
      title: '<#$field.desc#>',
      dataIndex: '<#$field.name#>',
      key: '<#$field.name#>',
    },
 <#/foreach#>
      {
      title: '操作',
      key: 'operation',
      width: 100,
      render: (text, record) => {
        return <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '1', name: '编辑' }, { key: '2', name: '删除' }]} />
      },
    },
  ]

  const getBodyWrapperProps = {
    page: location.query.page,
    current: pagination.current,
  }

  const getBodyWrapper = body => { return isMotion ? <TableBodyWrapper {...getBodyWrapperProps} body={body} /> : body }

  return (
    <div>
      <Table
        className={classnames({ [styles.table]: true, [styles.motion]: isMotion })}
        bordered
        scroll={{ x: columns.length * 150 }}
        columns={columns}
        dataSource={dataSource}
        loading={loading}
        onChange={onPageChange}
        pagination={pagination}
        simple
        rowKey={record => record.id}
        getBodyWrapper={getBodyWrapper}
      />
    </div>
  )
}

list.propTypes = {
  loading: PropTypes.bool,
  dataSource: PropTypes.array,
  pagination: PropTypes.object,
  onPageChange: PropTypes.func,
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
}

export default list
