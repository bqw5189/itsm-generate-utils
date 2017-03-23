import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import <#$entity.entityClassName#>List from '../components/<#$entity.path#>/list'
import <#$entity.entityClassName#>Search from '../components/<#$entity.path#>/search'
import <#$entity.entityClassName#>Modal from '../components/<#$entity.path#>/modal'

function <#$entity.entityClassName#> ({ location, dispatch, <#$entity.path#>, loading }) {
  const { list, pagination, currentItem, modalVisible, modalType, isMotion } = <#$entity.path#>
  const { field, keyword } = location.query
  const parentKey = "auto";
  
  const <#$entity.fieldClassName#>ModalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `<#$entity.path#>/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: '<#$entity.path#>/hideModal',
      })
    },
  }

  const <#$entity.fieldClassName#>ListProps = {
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
        type: '<#$entity.path#>/delete',
        payload: id,
      })
    },
    onEditItem (item) {
      dispatch({
        type: '<#$entity.path#>/showModal',
        payload: {
          modalType: 'update',
          currentItem: item,
        },
      })
    },
  }

  const <#$entity.path#>earchProps = {
    field,
    keyword,
    isMotion,
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/' + parentKey + '/<#$entity.path#>',
        query: {
          field: fieldsValue.field,
          keyword: fieldsValue.keyword,
        },
      })) : dispatch(routerRedux.push({
        pathname: '/' + parentKey + '/<#$entity.path#>',
      }))
    },
    onAdd () {
      dispatch({
        type: '<#$entity.path#>/showModal',
        payload: {
          modalType: 'create',
        },
      })
    },
    switchIsMotion () {
      dispatch({ type: '<#$entity.path#>/switchIsMotion' })
    },
  }

  const <#$entity.entityClassName#>ModalGen = () =>
    <<#$entity.entityClassName#>Modal {...<#$entity.fieldClassName#>ModalProps} />

  return (
    <div className="content-inner">
      <<#$entity.entityClassName#>Search {...<#$entity.path#>earchProps} />
      <<#$entity.entityClassName#>List {...<#$entity.fieldClassName#>ListProps} />
      <<#$entity.entityClassName#>ModalGen />
    </div>
  )
}

<#$entity.entityClassName#>.propTypes = {
  <#$entity.path#>: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({ <#$entity.path#>, loading }) => ({ <#$entity.path#>, loading: loading.models.user }))(<#$entity.entityClassName#>)
