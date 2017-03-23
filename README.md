# itsm-generate-utils
itsm-generate-utils


# ui

## menu
> /work/001_code/github/js/antd-admin/src/utils/menu.js

```json 
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
  }
```
## router
> /work/001_code/github/js/antd-admin/src/router.js

```json 
{
  path: 'users',
  name: 'users',
  getComponent (nextState, cb) {
    require.ensure([], require => {
      registerModel(app, require('./models/users'))
      cb(null, require('./routes/users'))
    }, 'users')
  },
},
```

## views
> /work/001_code/github/js/antd-admin/src/routes/users.js

```json 
import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import UserList from '../components/users/list'
import UserSearch from '../components/users/search'
import UserModal from '../components/users/modal'

function Users ({ location, dispatch, users, loading }) {
  const { list, pagination, currentItem, modalVisible, modalType, isMotion } = users
  const { field, keyword } = location.query

  const userModalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `users/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'users/hideModal',
      })
    },
  }

  const userListProps = {
    dataSource: list,
    loading,
    pagination,
    location,
    isMotion,
    onPageChange (page) {
      const { query, pathname } = location
      dispatch(routerRedux.push({
        pathname,
        query: {
          ...query,
          page: page.current,
          pageSize: page.pageSize,
        },
      }))
    },
    onDeleteItem (id) {
      dispatch({
        type: 'users/delete',
        payload: id,
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'users/showModal',
        payload: {
          modalType: 'update',
          currentItem: item,
        },
      })
    },
  }

  const userSearchProps = {
    field,
    keyword,
    isMotion,
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/users',
        query: {
          field: fieldsValue.field,
          keyword: fieldsValue.keyword,
        },
      })) : dispatch(routerRedux.push({
        pathname: '/users',
      }))
    },
    onAdd () {
      dispatch({
        type: 'users/showModal',
        payload: {
          modalType: 'create',
        },
      })
    },
    switchIsMotion () {
      dispatch({ type: 'users/switchIsMotion' })
    },
  }

  const UserModalGen = () =>
    <UserModal {...userModalProps} />

  return (
    <div className="content-inner">
      <UserSearch {...userSearchProps} />
      <UserList {...userListProps} />
      <UserModalGen />
    </div>
  )
}

Users.propTypes = {
  users: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({ users, loading }) => ({ users, loading: loading.models.user }))(Users)

```

## components
> /work/001_code/github/js/antd-admin/src/components/users/list.js

```json
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
    {
      title: '头像',
      dataIndex: 'avatar',
      key: 'avatar',
      width: 64,
      className: styles.avatar,
      render: (text) => <img alt={'avatar'} width={24} src={text} />,
    }, {
      title: '姓名',
      dataIndex: 'name',
      key: 'name',
    }, {
      title: '昵称',
      dataIndex: 'nickName',
      key: 'nickName',
    }, {
      title: '年龄',
      dataIndex: 'age',
      key: 'age',
      render: (text) => <span>{text}岁</span>,
    }, {
      title: '性别',
      dataIndex: 'isMale',
      key: 'isMale',
      render: (text) => <span>{text
            ? '男'
            : '女'}</span>,
    }, {
      title: '电话',
      dataIndex: 'phone',
      key: 'phone',
    }, {
      title: '邮箱',
      dataIndex: 'email',
      key: 'email',
    }, {
      title: '住址',
      dataIndex: 'address',
      key: 'address',
    }, {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
    }, {
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
        scroll={{ x: 1200 }}
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

```
> /work/001_code/github/js/antd-admin/src/components/users/list.less

```css
.table {
  td.avatar {
    img {
      border-radius: 50%;
    }
  }

  :global {
    .ant-table-tbody > tr > td,
    .ant-table-thead > tr > th {
      height: 62px;
    }
  }

  &.motion {
    :global {
      .ant-table-tbody > tr > td,
      .ant-table-thead > tr > th {
        &:nth-child(1) {
          width: 6%;
        }

        &:nth-child(2) {
          width: 8%;
        }

        &:nth-child(3) {
          width: 8%;
        }

        &:nth-child(4) {
          width: 6%;
        }

        &:nth-child(5) {
          width: 6%;
        }

        &:nth-child(6) {
          width: 8%;
        }

        &:nth-child(7) {
          width: 16%;
        }

        &:nth-child(8) {
          width: 20%;
        }

        &:nth-child(9) {
          width: 14%;
        }

        &:nth-child(10) {
          width: 8%;
        }
      }

      .ant-table-thead {
        & > tr {
          transition: none;
          display: block;

          & > th {
            display: inline-flex;
            align-items: center;
            justify-content: center;
          }
        }
      }

      .ant-table-tbody {
        & > tr {
          transition: none;
          display: block;
          border-bottom: 1px solid #f5f5f5;

          & > td {
            border-bottom: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
          }

          &.ant-table-expanded-row-level-1 > td {
            height: auto;
          }
        }
      }
    }
  }
}

```
> /work/001_code/github/js/antd-admin/src/components/users/modal.js

```json

import React, { PropTypes } from 'react'
import { Form, Input, InputNumber, Radio, Modal } from 'antd'
const FormItem = Form.Item

const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
}

const modal = ({
  visible,
  type,
  item = {},
  onOk,
  onCancel,
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
  },
}) => {
  function handleOk () {
    validateFields((errors) => {
      if (errors) {
        return
      }
      const data = {
        ...getFieldsValue(),
        key: item.key,
      }
      onOk(data)
    })
  }

  const modalOpts = {
    title: `${type === 'create' ? '新建用户' : '修改用户'}`,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal',
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="姓名：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('name', {
            initialValue: item.name,
            rules: [
              {
                required: true,
                message: '姓名未填写',
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="昵称：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('nickName', {
            initialValue: item.nickName,
            rules: [
              {
                required: true,
                message: '昵称未填写',
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="性别" hasFeedback {...formItemLayout}>
          {getFieldDecorator('isMale', {
            initialValue: item.isMale,
            rules: [
              {
                required: true,
                type: 'boolean',
                message: '请选择性别',
              },
            ],
          })(
            <Radio.Group>
              <Radio value>男</Radio>
              <Radio value={false}>女</Radio>
            </Radio.Group>
          )}
        </FormItem>
        <FormItem label="年龄：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('age', {
            initialValue: item.age,
            rules: [
              {
                required: true,
                type: 'number',
                message: '年龄未填写',
              },
            ],
          })(<InputNumber min={18} max={100} />)}
        </FormItem>
        <FormItem label="电话：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('phone', {
            initialValue: item.phone,
            rules: [
              {
                required: true,
                message: '不能为空',
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="邮箱：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('email', {
            initialValue: item.email,
            rules: [
              {
                required: true,
                message: '不能为空',
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="住址：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('address', {
            initialValue: item.address,
            rules: [
              {
                required: true,
                message: '不能为空',
              },
            ],
          })(<Input />)}
        </FormItem>
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  visible: PropTypes.bool,
  type: PropTypes.string,
  item: PropTypes.object,
  onCancel: PropTypes.func,
  onOk: PropTypes.func,
}

export default Form.create()(modal)

```
> /work/001_code/github/js/antd-admin/src/components/users/search.js

```json

import React, { PropTypes } from 'react'
import { Form, Button, Row, Col, Switch } from 'antd'
import SearchGroup from '../ui/search'

const search = ({
  field,
  keyword,
  onSearch,
  onAdd,
  isMotion,
  switchIsMotion,
}) => {
  const searchGroupProps = {
    field,
    keyword,
    size: 'large',
    select: true,
    selectOptions: [{ value: 'name', name: '姓名' }, { value: 'address', name: '地址' }],
    selectProps: {
      defaultValue: field || 'name',
    },
    onSearch: (value) => {
      onSearch(value)
    },
  }

  return (
    <Row gutter={24}>
      <Col lg={8} md={12} sm={16} xs={24} style={{ marginBottom: 16 }}>
        <SearchGroup {...searchGroupProps} />
      </Col>
      <Col lg={{ offset: 8, span: 8 }} md={12} sm={8} xs={24} style={{ marginBottom: 16, textAlign: 'right' }}>
        <Switch style={{ marginRight: 16 }} defaultChecked={isMotion} onChange={switchIsMotion} checkedChildren={'动画开'} unCheckedChildren={'动画关'} />
        <Button size="large" type="ghost" onClick={onAdd}>添加</Button>
      </Col>
    </Row>
  )
}

search.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  onAdd: PropTypes.func,
  field: PropTypes.string,
  keyword: PropTypes.string,
  isMotion: PropTypes.bool,
  switchIsMotion: PropTypes.func,
}

export default Form.create()(search)

```
> /work/001_code/github/js/antd-admin/src/components/users/search.less

```css

Error: Source sample is missing.

```


## services
> /work/001_code/github/js/antd-admin/src/services/users.js

```json 
import { request } from '../utils'

export async function query (params) {
  return request('/api/users', {
    method: 'get',
    data: params,
  })
}

export async function create (params) {
  return request('/api/users', {
    method: 'post',
    data: params,
  })
}

export async function remove (params) {
  return request('/api/users', {
    method: 'delete',
    data: params,
  })
}

export async function update (params) {
  return request('/api/users', {
    method: 'put',
    data: params,
  })
}

```
## models
> /work/001_code/github/js/antd-admin/src/models/users.js

```json 
import { create, remove, update, query } from '../services/users'
import { parse } from 'qs'

export default {

  namespace: 'users',

  state: {
    list: [],
    currentItem: {},
    modalVisible: false,
    modalType: 'create',
    isMotion: localStorage.getItem('antdAdminUserIsMotion') === 'true',
    pagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null,
    },
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname === '/users') {
          dispatch({
            type: 'query',
            payload: location.query,
          })
        }
      })
    },
  },

  effects: {
    *query ({ payload }, { call, put }) {
      const data = yield call(query, parse(payload))
      if (data) {
        yield put({
          type: 'querySuccess',
          payload: {
            list: data.data,
            pagination: data.page,
          },
        })
      }
    },
    *'delete' ({ payload }, { call, put }) {
      const data = yield call(remove, { id: payload })
      if (data && data.success) {
        yield put({
          type: 'querySuccess',
          payload: {
            list: data.data,
            pagination: {
              total: data.page.total,
              current: data.page.current,
            },
          },
        })
      }
    },
    *create ({ payload }, { call, put }) {
      yield put({ type: 'hideModal' })
      const data = yield call(create, payload)
      if (data && data.success) {
        yield put({
          type: 'querySuccess',
          payload: {
            list: data.data,
            pagination: {
              total: data.page.total,
              current: data.page.current,
            },
          },
        })
      }
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' })
      const id = yield select(({ users }) => users.currentItem.id)
      const newUser = { ...payload, id }
      const data = yield call(update, newUser)
      if (data && data.success) {
        yield put({
          type: 'querySuccess',
          payload: {
            list: data.data,
            pagination: {
              total: data.page.total,
              current: data.page.current,
            },
          },
        })
      }
    },
    *switchIsMotion ({
      payload,
    }, { put }) {
      yield put({
        type: 'handleSwitchIsMotion',
      })
    },
  },

  reducers: {
    querySuccess (state, action) {
      const { list, pagination } = action.payload
      return { ...state,
        list,
        pagination: {
          ...state.pagination,
          ...pagination,
        } }
    },
    showModal (state, action) {
      return { ...state, ...action.payload, modalVisible: true }
    },
    hideModal (state) {
      return { ...state, modalVisible: false }
    },
    handleSwitchIsMotion (state) {
      localStorage.setItem('antdAdminUserIsMotion', !state.isMotion)
      return { ...state, isMotion: !state.isMotion }
    },
  },

}


```
