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
    title: `${type === 'create' ? '新建' : '修改'}`,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal',
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
      <#foreach from=$entity.fields item=field#>
          <FormItem label="<#$field.desc#>：" hasFeedback {...formItemLayout}>
            {getFieldDecorator('<#$field.name#>', {
              initialValue: item.<#$field.name#>,
              rules: [
                {
                  required: true,
                  message: '<#$field.desc#>未填写',
                },
              ],
            })(<Input />)}
          </FormItem>
       <#/foreach#>
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
