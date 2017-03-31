import { request } from '../utils'
import {paramsFormat, dataFormat} from '../utils/pageFormat'

export async function query (params) {
  return request('/api/<#$entity.path#>/page', {
    method: 'post',
    data: paramsFormat(params)
  }).done((data) => {
    return dataFormat(data)
  })
}

export async function create (params) {
  return request('/api/<#$entity.path#>', {
    method: 'post',
    contentType:'application/json;charset=UTF-8',
    data: params,
  })
}

export async function remove (params) {
  return request('/api/<#$entity.path#>/' + params.id, {
    method: 'delete'
  })
}

export async function update (params) {
  return request('/api/<#$entity.path#>', {
    method: 'post',
    data: params,
  })
}

