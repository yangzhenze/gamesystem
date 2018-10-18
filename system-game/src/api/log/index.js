import request from '@/utils/request'

// 登录日志
export function getLoginList(page, params) {
  return request({
    url: '/api/log/login/' + page,
    method: 'get',
    params
  })
}
// 创角日志
export function getCreateRoleList(page, params) {
  return request({
    url: '/api/log/createrole/' + page,
    method: 'get',
    params
  })
}

