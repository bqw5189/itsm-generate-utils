import { request } from '../utils'

export async function query (params) {
  return request('/api/<#$entity.path#>', {
    method: 'get',
    data: params,
  })
}

export async function create (params) {
  return request('/api/<#$entity.path#>', {
    method: 'post',
    data: params,
  })
}

export async function remove (params) {
  return request('/api/<#$entity.path#>', {
    method: 'delete',
    data: params,
  })
}

export async function update (params) {
  return request('/api/<#$entity.path#>', {
    method: 'put',
    data: params,
  })
}
